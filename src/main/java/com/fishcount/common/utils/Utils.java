package com.fishcount.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.util.ObjectUtils;

import javax.swing.text.MaskFormatter;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author lucas
 */
public class Utils {

    private Utils(){
    }

    public static boolean isEmpty(Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * Retorna o primeiro valor não nulo dos argumentos.
     *
     * @param <T>
     * @param originalInstance valor prioritário
     * @param returnIfNull     valor a ser utilizado se {@code originalInstance} não
     *                         existir
     * @return um dos argumentos
     */
    @SuppressWarnings("unchecked")
    public static <T> T nvl(Object originalInstance, T returnIfNull) {
        if (originalInstance instanceof String) {
            String a = (String) originalInstance;
            if (a.trim().isEmpty()) {
                return returnIfNull;
            }
        }
        return (T) ((originalInstance == null) ? returnIfNull : originalInstance);
    }

    public static String formatter(String value, String mask) {
        try {
            if (StringUtil.isNotNullOrEmpty(value) && StringUtil.isNotNullOrEmpty(mask)) {
                MaskFormatter maskFormatter = new MaskFormatter(mask);
                maskFormatter.setValueContainsLiteralCharacters(false);

                value = maskFormatter.valueToString(value);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String formatarCep(String cep) {
        try {
            if (!isEmpty(cep)) {
                MaskFormatter mf = new MaskFormatter("#####-###");
                mf.setValueContainsLiteralCharacters(false);
                cep = mf.valueToString(numbers(cep));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return cep;
    }

    public static String numbers(String str) {
        String n = nvl(str, "");
        return n.replaceAll("[^0-9]", "");
    }

    public static String removeAcentos(String s) {
        if (s == null) {
            return "";
        }
        CharSequence cs = new StringBuilder(s);
        String sRet = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+",
                "");

        String[] caracteresEspeciais = { "\\.", ",", "-", ":", "\\(", "\\)", "ª", "\\|", "\\\\", "°", "/", "–", "|" };

        for (String caracteresEspeciai : caracteresEspeciais) {
            sRet = sRet.replaceAll(caracteresEspeciai, "");
        }
        sRet = sRet.replace("&", "E");

        return sRet;
    }

    public static List<String> searchBetweenStringsToNumbers(final String text, final String start, final String end) {
        final String quoteStart = Pattern.quote(start);
        final String quoteEnd = Pattern.quote(end);

        final Pattern pattern = Pattern.compile(quoteStart + "(.*?)" + quoteEnd);
        final Matcher matcher = pattern.matcher(text);

        final List<String> successMath = new ArrayList<>();

        while (matcher.find()) {
            successMath.add(matcher.group(1));
        }

        return successMath.stream()
                .map(item -> item.replaceAll("[^a-zA-Z0-9]", ""))
                .collect(Collectors.toList());
    }

    public static String objectToJson(Object obj) {
        final Gson gsonResponse = new GsonBuilder().create();
        return gsonResponse.toJson(obj);
    }

    public static <T> T jsonToObject(String jsonString, Class<T> clzz) {
        Gson g = new Gson();

        return g.fromJson(jsonString, clzz);
    }

    public static boolean equals(Object val1, Object val2) {
        if (val1 == null && val2 == null) {
            return true;
        } else if (val1 != null && val2 != null) {
            if (val1.getClass().isPrimitive()) {
                return val1 == val2;
            } else {
                return val1.equals(val2);
            }
        } else {
            return false;
        }
    }



}
