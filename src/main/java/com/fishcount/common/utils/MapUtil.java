
package com.fishcount.common.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MapUtil {

      public static <K> Double putIncrement(Map<K, Double> map, K key, Double value) {
        if (value == null) {
            value = 0.0;
        }

        Double oldValue = map.get(key);
        if (oldValue != null) {
            value += oldValue;
        }

        return map.put(key, value);
    }

    public static <K> BigDecimal putIncrement(Map<K, BigDecimal> map, K key, BigDecimal value) {
        if (value == null) {
            value = BigDecimal.ZERO;
        }

        BigDecimal oldValue = map.get(key);
        if (oldValue != null) {
            value = value.add(oldValue);
        }

        return map.put(key, value);
    }

    public static <K> Integer putIncrement(Map<K, Integer> map, K key) {
        Integer oldValue = map.get(key);
        if (oldValue != null) {
            oldValue++;
        } else {
            oldValue = 1;
        }

        return map.put(key, oldValue);
    }

    public static boolean isNotNullOrEmpty(Map<?, ?> map) {
        return !isNullOrEmpty(map);
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
