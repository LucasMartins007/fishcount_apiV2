
package com.fishcount.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author lucas
 */
public class NumericUtil {

    NumericUtil() {
    }

    public static double round(Double valor) {
        return round(valor, 2);
    }

    public static double round(Double valor, Integer casas) {
        if (valor == null || valor.isNaN()) {
            valor = 0.0;
        }

        if (casas == null) {
            casas = 2;
        }

        BigDecimal big = new BigDecimal(valor.toString());
        big = big.setScale(casas, RoundingMode.HALF_UP);
        return big.doubleValue();
    }

    public static BigDecimal round(BigDecimal valor) {
        return round(valor, 2);
    }

    public static BigDecimal round(BigDecimal valor, Integer casas) {
        if (valor == null) {
            valor = BigDecimal.ZERO.setScale(casas, RoundingMode.HALF_UP);
        }
        if (casas == null) {
            casas = 2;
        }
        return valor.setScale(casas, RoundingMode.HALF_UP);
    }

    public static synchronized boolean isEquals(BigDecimal one, BigDecimal other) {
        if (one != null && other == null) {
            return false;
        } else if (one == null && other != null) {
            return false;
        } else {
            return one != null && one.compareTo(other) == 0;
        }

    }

    public static synchronized boolean isGreater(Number number, Number compareTo) {
        return number != null && number.doubleValue() > compareTo.doubleValue();
    }

    public static synchronized boolean isGreaterOrEquals(Number number, Number compareTo) {
        return number != null && number.doubleValue() >= compareTo.doubleValue();
    }

    public static synchronized boolean isGreaterThanZero(Number number) {
        return isGreater(number, 0);
    }

    public static synchronized boolean isLess(Number number, Number compareTo) {
        return number != null && number.doubleValue() < compareTo.doubleValue();
    }

    public static synchronized boolean isLessOrEquals(Number number, Number compareTo) {
        return number != null && number.doubleValue() <= compareTo.doubleValue();
    }

    public static synchronized boolean isLessThanZero(Number number) {
        return isLess(number, 0);
    }

    public static synchronized double getPercentage(double value, double percent) {
        value = value * (percent / 100);

        return value;
    }

    public static synchronized BigDecimal getPercentage(BigDecimal value, BigDecimal percent) {
        double percentage = getPercentage(value.doubleValue(), percent.doubleValue());
        return BigDecimal.valueOf(percentage);
    }

    public static synchronized BigDecimal getNotNullOrZero(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    public static synchronized Integer getNotNullOrZero(Integer value) {
        return value != null ? value : 0;
    }

    public static synchronized Byte getNotNullOrZero(Byte value) {
        return value != null ? value : Byte.parseByte("0");
    }

    public static synchronized Long getNotNullOrZero(Long value) {
        return value != null ? value : Long.parseLong("0");
    }

    public static synchronized Float getNotNullOrZero(Float value) {
        return value != null ? value : Float.valueOf(0);
    }

    public static synchronized Double getNotNullOrZero(Double value) {
        return value != null ? value : 0.0;
    }

    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return BigDecimal.valueOf(Math.min(a.doubleValue(), b.doubleValue()));
    }

    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        return BigDecimal.valueOf((Math.max(a.doubleValue(), b.doubleValue())));
    }

    public static boolean isNumeric(String str) {
        return str.matches("^-?\\d(\\.\\d+)?$");
    }

    public static synchronized boolean isIntegerValue(Double value) {
        return value != null && value % 1 == 0;
    }

    public static boolean between(Number valor, Number inicio, Number fim) {
        if (valor == null || inicio == null || fim == null) {
            return false;
        }
        return valor.doubleValue() >= inicio.doubleValue() && valor.doubleValue() <= fim.doubleValue();
    }

    public static Integer parseInt(String strVal) {
        try {
            if (strVal != null) {
                return Integer.parseInt(strVal);
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public static Integer parseInt(Object objVal) {
        if (objVal != null) {
            return parseInt(objVal.toString());
        }
        return null;
    }

    public static String somenteNumero(String s) {

        String sRet = "";

        if (s != null) {
            sRet = numbers(s);
        }

        return sRet;
    }

    public static String numbers(String str) {
        String n = Utils.nvl(str, "");
        return n.replaceAll("\\d", "");
    }

}
