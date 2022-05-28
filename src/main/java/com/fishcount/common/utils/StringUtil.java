package com.fishcount.common.utils;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author lucas
 */
public class StringUtil extends StringUtils {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty() || str.equalsIgnoreCase("null");
    }

    public static boolean isNullOrEmpty(Object str) {
        return str == null || str.toString().trim().isEmpty();
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static String toLowerCase(String text) {
        if (isNotNullOrEmpty(text)) {
            return text.toLowerCase();
        }
        return null;
    }

    public static String toStringNotNull(Object value) {
        return value != null ? value.toString() : "";
    }

    public static String toString(Object value) {
        return value != null ? value.toString() : null;
    }

    public static boolean isValidEmail(String email) {
        if (isNotNullOrEmpty(email)) {
            String regex = "[a-zA-Z0-9-_\\.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+";

            return Pattern.matches(regex, email);
        }
        return true;
    }

    /**
     * @param messagePattern "example {0}"
     * @param argumentsMessage "1"
     * @return "example 1"
     */
    public static String formatMessage(String messagePattern, Object... argumentsMessage) {
        return MessageFormat.format(messagePattern, argumentsMessage);
    }

    public static String keepOnlyNumbers(String s) {
        if (s == null) {
            return null;
        } else {
            return s.replaceAll("\\D", "");
        }
    }

    public static String firstWord(String texto) {
        if (StringUtil.isNotNullOrEmpty(texto)) {
            final StringTokenizer tokenizer = new StringTokenizer(texto);
            final String text = tokenizer.nextToken().toString();
            if (text.length() < 4 && tokenizer.hasMoreTokens()) {
                return text + " " + tokenizer.nextToken();
            }
            return text;
        }

        return texto;
    }

    public static String normalize(String value) {
        if (value == null) {
            return "";
        }
        value = value.trim();
        value = value.replaceAll("[^\\p{L}\\p{N}]", " ").replaceAll("[ ]+", "-");
        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        value = value.replaceAll("[^\\p{ASCII}]", "");

        if (value.endsWith("-")) {
            value.subSequence(0, value.length() - 1);
        }
        value = value.replaceAll("[^\\p{ASCII}]", "");
        return value;
    }

    public static String randomIdentifier(final Integer qtd, final Boolean caseSensitive) {
        final String lexicon = caseSensitive == null || !caseSensitive ? "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890" : "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890abcdefghijklmnopqrstuvxyz";
        final java.util.Random rand = new java.util.Random();
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = qtd != null ? qtd : rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }

    public static String capitalize(String value) {
        if (StringUtil.isNotNullOrEmpty(value)) {
            value = value.trim().toLowerCase();
            if (value.contains(" ")) {
                return Stream.of(value.split(" "))
                        .map(StringUtil::capitalize)
                        .collect(Collectors.joining(" ", "", ""));
            } else {
                return StringUtil.capitalize(value);
            }
        }
        return null;
    }

    public static String capitalizeAndShorthand(final String nomeCliente) {
        final String capitalize = StringUtil.capitalize(nomeCliente);
        return StringUtil.firstWord(capitalize);
    }

    public static String formataMoeda(Number valor) {
        String vlrFormatado;
        NumberFormat formatter = new DecimalFormat("#,##0.00");

        try {
            vlrFormatado = formatter.format(valor);
        } catch (IllegalArgumentException ex) {
            vlrFormatado = formatter.format(0.0d);
        }

        return vlrFormatado;
    }

    
}
