/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fishcount.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lucas
 */
public class BigDecimalUtil {

    BigDecimalUtil() {
    }

    public static BigDecimal valueOf(String value) {
        if (value != null) {
            Double doubleValue = Double.valueOf(value);
            return BigDecimal.valueOf(doubleValue);
        }
        return null;
    }

    public static BigDecimal truncBig(BigDecimal value, Integer decimais) {
        value = value.setScale(decimais, RoundingMode.FLOOR);
        return value;
    }

    public static BigDecimal divide(BigDecimal value, BigDecimal divisor, Integer casasDecimais) {
        if (value != null && casasDecimais != null) {
            return value.divide(divisor, casasDecimais, RoundingMode.FLOOR);
        }
        if (value != null){
            return value.divide(divisor, RoundingMode.FLOOR);
        }
        return BigDecimal.ZERO;
    }
}
