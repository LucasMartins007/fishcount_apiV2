/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fishcount.common.utils;

import java.math.BigDecimal;

/**
 *
 * @author lucas
 */
class BigDecimalUtil {
    
     public static BigDecimal valueOf(String value) {
        if (value != null) {
            Double doubleValue = Double.valueOf(value);
            return BigDecimal.valueOf(doubleValue);
        }
        return null;
    }

    public static BigDecimal truncBig(BigDecimal value, Integer decimais) {
        value = value.setScale(decimais, BigDecimal.ROUND_FLOOR);
        return value;
    }
}
