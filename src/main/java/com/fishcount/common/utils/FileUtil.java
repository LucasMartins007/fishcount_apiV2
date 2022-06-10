package com.fishcount.common.utils;

import com.fishcount.common.exception.FcRuntimeException;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Lucas Martins
 */
public class FileUtil {

    private FileUtil() {
        // CONSTRUTOR PADRAO
    }

    public static File createFile(String content, String path) throws IOException {
        File file = new File(path);
        try {
            FileUtils.writeStringToFile(file, content, "UTF-8");
        } catch (Exception ex) {

            throw ex;
        }
        return file;
    }

    public static File createFile(byte[] bytes, String path) throws IOException {
        File file = new File(path);
        try {
            FileUtils.writeByteArrayToFile(file, bytes);
        } catch (IOException ex) {

            throw ex;
        }
        return file;
    }

    public static String fileName(String caminho, String nome, String extensao) {
        boolean fileExists = true;
        Integer count = 1;
        StringBuilder sb;

        while (fileExists) {
            sb = new StringBuilder();
            sb.append(caminho).append(nome).append("-").append(count.toString()).append(".").append(extensao);
            if (!new File(sb.toString()).exists()) {
                fileExists = false;
            } else {
                count++;
            }
        }
        return nome.concat("-".concat(count.toString()));
    }

    public static File writeFile(String file, String content) throws IOException {
        File arquivo;

        String[] split = StringUtil.split(file, "/");

        String concat = "/";
        for (int i = 0; i < split.length - 1; i++) {
            String s = split[i];
            concat += s + "/";

            concat = concat.replaceAll("[/]+", "/");
            arquivo = new File(concat);

            if (!arquivo.exists()) {
                arquivo.mkdirs();
            }

        }
        arquivo = new File(file);

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        if (!arquivo.isFile()) {
            throw new IOException("Não é um arquivo");
        } else if (!arquivo.canWrite()) {
            throw new IOException("Arquivo sem permissão para escrita");
        }

        try ( FileWriter writer = new FileWriter(arquivo, false);  PrintWriter saida = new PrintWriter(writer, false);) {
            writer.flush();
            saida.println(content);
        }

        return arquivo;
    }

    public static long fileCheckSum(File file) throws IOException {
        try ( FileInputStream input = new FileInputStream(file);  CheckedInputStream cis = new CheckedInputStream(input, new CRC32())) {
            byte[] buf = new byte[4096];
            while (cis.read(buf) > 0)
                ;
            return cis.getChecksum().getValue();
        }
    }

    public static boolean fileEquals(File f01, File f02) {
        try {
            return fileCheckSum(f01) == fileCheckSum(f02);
        } catch (IOException ex) {

        }
        return false;
    }

    public static BufferedImage readImage(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IOException("Arquivo não existe " + (file != null ? file.getAbsolutePath() : ""));
        }
        BufferedImage bi;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {

            bi = readFromCmyk(file);
        }

        if (bi == null) {
            throw new IOException("Formato ou conteudo do arquivo inválido ou não suportado");
        }
        return bi;
    }

    private static BufferedImage readFromCmyk(File f) {
        try {
            // Find a suitable ImageReader
            Iterator readers = ImageIO.getImageReadersByFormatName("JPEG");
            ImageReader reader = null;
            while (readers.hasNext()) {
                reader = (ImageReader) readers.next();
                if (reader.canReadRaster()) {
                    break;
                }
            }

            if (reader != null) {
                // Stream the image file (the original CMYK image)
                ImageInputStream input = ImageIO.createImageInputStream(f);
                reader.setInput(input);
                // Read the image raster
                Raster raster = reader.readRaster(0, null);
                // Create a new RGB image
                BufferedImage bi = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                // Fill the new image with the old raster
                bi.getRaster().setRect(raster);
                return bi;
            }
        } catch (Exception e) {

        }
        return null;
    }

    private static void doLogFile(Boolean log, String idData, String logData) {
        try {

            if (log) {
                new Thread(new LogFile(idData, logData)).start();
            }
        } catch (Exception e) {

        }
    }

    public static boolean exists(String fileOrDirectory) {
        try {
            return new File(fileOrDirectory).exists();
        } catch (Exception e) {

        }
        return false;
    }

    public static <T> T loadObject(File file) {
        T obj = null;
        try {
            if (file.exists()) {
                InputStream finput = new FileInputStream(file);
                InputStream buffer = new BufferedInputStream(finput);
                ObjectInput input = new ObjectInputStream(buffer);

                try {
                    obj = (T) input.readObject();
                } finally {
                    input.close();
                }
            }
        } catch (Exception e) {

        }
        return obj;
    }

    public static void saveObject(Object obj, File file) {
        try {

            OutputStream outfile = new FileOutputStream(file);
            OutputStream buffer = new BufferedOutputStream(outfile);
            ObjectOutput output = new ObjectOutputStream(buffer);

            try {
                output.writeObject(obj);
            } finally {
                output.close();
            }

        } catch (Exception e) {

        }
    }

    public static String pathExists(String string) throws IOException {
        String[] paths = string.split("/");
        String absolutePath = "";
        absolutePath += "/";
        for (String path : paths) {

            absolutePath += path;
            absolutePath += "/";
            File file = new File(absolutePath);
            if (!path.endsWith("html")) {

                if (!file.exists()) {
                    // can Create ?
                    file.mkdir();
                }

            } else if (!file.exists()) {
                // can Create ?
                file.createNewFile();
            }
        }

        return absolutePath;
    }

    public static byte[] getFileContentFromFile(File file) {
        byte[] bytes = null;
        try ( FileInputStream is = new FileInputStream(file);  FileChannel ch = is.getChannel()) {
            int size = (int) ch.size();
            MappedByteBuffer buf = ch.map(FileChannel.MapMode.READ_ONLY, 0, size);
            bytes = new byte[size];
            buf.get(bytes);
        } catch (IOException e) {
            throw new FcRuntimeException(e);
        }
        return bytes;
    }

    public static String getOSTempDir() {
        String tempDir = System.getProperty("java.io.tmpdir");

        if (tempDir.charAt(tempDir.length() - 1) != File.separatorChar) {
            tempDir += File.separatorChar;
        }

        return tempDir;
    }

    public static byte[] getInputStream(InputStream inputStream) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new FcRuntimeException(e);
        }
    }

    public static String getExtension(String path) {
        return FilenameUtils.getExtension(path);
    }

    public static String getFileName(String path) {
        return FilenameUtils.getBaseName(path);
    }

    private static class LogFile implements Runnable {

        private String idData;

        private String logData;

        public LogFile(String idData, String logData) {
            super();
            this.idData = idData;
            this.logData = logData;
        }

        @Override
        public void run() {
            try {
                final SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");

                String id = idData;
                String log = logData;

                if (id == null) {
                    id = "ID: " + System.currentTimeMillis();
                }

                StringBuilder sb = new StringBuilder();
                sb.append(System.getProperty("user.home"));
                sb.append("/oceanlogs/");

                String path = sb.toString();

                File file = new File(path);

                if (!file.exists()) {
                    file.mkdirs();
                }

                file = new File(path + "ejb_log.log");
                if (!file.exists()) {
                    file.createNewFile();
                }
                log = id.concat(" ").concat(log);
                log = log.trim();
                log = log.replaceAll("\n", " ");
                log = log.replaceAll("[\\s+]", " ");
                log = log.concat("\n");

                FileUtils.writeStringToFile(file, log, Charset.defaultCharset(), true);
                long size = file.length();

                if (((size / 1024) / 1024) >= 2) {
                    String fileName = "ejb_log_" + format.format(new Date()) + ".log";
                    FileUtils.moveFile(new File(path + "ejb_log.log"), new File(path.concat(fileName)));
                }
            } catch (Exception e) {
            }
        }
    }
}
