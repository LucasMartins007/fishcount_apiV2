package com.fishcount.common.utils;

import com.fishcount.common.exception.FcRuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Lucas Martins
 */
public class HttpUtils {

    private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*\"?([^\\s;\"]*)");

    public static String request(String url, String user_agent) {
        try {
            URL urlOpen = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlOpen.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000 * 5);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            if (user_agent != null) {
                conn.setRequestProperty("User-Agent", user_agent);
            }

            if (url.contains("https")) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(getSSLContextToIgnoreCertificate().getSocketFactory());
            }

            try ( InputStream is = conn.getInputStream()) {
                return IOUtils.toString(is, StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
//            LogUtils.err(ex);
        }
        return null;
    }

    public static void defineSSLContextToHttpsURLConnection() {
        HttpsURLConnection.setDefaultSSLSocketFactory(getSSLContextToIgnoreCertificate().getSocketFactory());
    }

    public static SSLContext getSSLContextToIgnoreCertificate() {
        try {
            TrustManager[] dummyTrustManager = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }};

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, dummyTrustManager, new java.security.SecureRandom());

            return sslContext;
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new FcRuntimeException(e);
        }
    }

    public static CloseableHttpClient getHttpClientSSLContextIgnoreCertificate() {
        HttpClientBuilder clientbuilder = HttpClients.custom();

        SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(getSSLContextToIgnoreCertificate(), new NoopHostnameVerifier());

        clientbuilder.setSSLSocketFactory(sslConSocFactory);

        CloseableHttpClient httpclient = clientbuilder.build();

        return httpclient;
    }

    public static boolean doRequest(Invocation.Builder header) {
        for (int i = 0; i < 100; i++) {
            long timeIni = System.currentTimeMillis();
            String message;

            try {
                Response response = header.get();

                message = String.format("HTTP connection status %s", response.getStatus());
            } catch (Exception e) {
                message = String.format("Error creating HTTP connection %s", e.getMessage());
            }

            System.out.println(Thread.currentThread().getName() + " - " + (System.currentTimeMillis() - timeIni) + "ms " + message);
        }

        return true;
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String ipLocal = null;
        try {
            ipLocal = request.getRemoteAddr();

            boolean forwarded = request.getHeader("X-FORWARDED-FOR") != null;

            if (ipLocal == null || ipLocal.equals("192.168.10.1") || forwarded) {
                ipLocal = request.getHeader("X-FORWARDED-FOR");

                if (ipLocal != null) {
                    int idx = ipLocal.indexOf(',');

                    if (idx > -1) {
                        ipLocal = ipLocal.substring(0, idx);
                    }
                } else {
                    ipLocal = request.getRemoteAddr();
                }
            }

            if (ipLocal.equals("127.0.0.1") || ipLocal.contains("localhost")) {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface iface = interfaces.nextElement();
                    // filters out 127.0.0.1 and inactive interfaces
                    if (iface.isLoopback() || !iface.isUp()) {
                        continue;
                    }

                    Enumeration<InetAddress> addresses = iface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        ipLocal = addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {

        }
        return ipLocal;
    }

    public static boolean isGzipped(MultivaluedMap<String, Object> headers) {
        List<Object> contentEncoding = Optional.ofNullable(headers.get(HttpHeaders.CONTENT_ENCODING)).orElse(new ArrayList<>());
        return contentEncoding.stream().anyMatch((s) -> {
            return ((String) s).contains("gzip");
        });
    }

    public static boolean isGzipped(Header contentEncoding) {
        if (contentEncoding != null) {
            String value = contentEncoding.getValue();
            if (value != null && "gzip".equals(value.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }

    public static List<NameValuePair> getList(Map<String, List<Object>> parameters) {
        List<NameValuePair> result = new ArrayList();
        if (parameters != null) {
            TreeMap<String, List<Object>> sortedParameters = new TreeMap<>(parameters);
            for (Map.Entry<String, List<Object>> entry : sortedParameters.entrySet()) {
                List<Object> entryValue = entry.getValue();
                if (entryValue != null) {
                    for (Object cur : entryValue) {
                        if (cur != null) {
                            result.add(new BasicNameValuePair(entry.getKey(), cur.toString()));
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Parse out a charset from a content type header.
     *
     * @param contentType e.g. "text/html; charset=EUC-JP"
     * @return "EUC-JP", or null if not found. Charset is trimmed and
     * uppercased.
     */
    public static String getCharsetFromContentType(String contentType) {
        if (contentType == null) {
            return null;
        }

        Matcher m = charsetPattern.matcher(contentType);
        if (m.find()) {
            return m.group(1).trim().toUpperCase();
        }
        return null;
    }

    private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
        }
    }

}
