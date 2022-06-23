package com.fishcount.common.utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author lucas
 */
public class ListUtil {

    ListUtil() {
    }

    public static boolean isNullOrEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNullOrEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotNullOrNotEmpty(Collection<?> list) {
        return !isNullOrEmpty(list);
    }

    public static <E> E first(Collection<E> list) {
        if (isNotNullOrNotEmpty(list)) {
            return list.iterator().next();
        }

        return null;
    }

    public static <E> E last(Collection<E> list) {
        E retorno = null;

        if (isNotNullOrNotEmpty(list)) {
            Iterator<E> iterator = list.iterator();
            while (iterator.hasNext()) {
                retorno = iterator.next();
            }
        }

        return retorno;
    }

    public static <T> List<T> toList(T... values) {
        return Arrays.asList(values);
    }

    public static <T> boolean addIfNotNull(Collection<T> collection, T value) {
        if (value != null) {
            return collection.add(value);
        }
        return false;
    }

    public static <T> void addAllIfNotNull(Collection<T> collection, Collection<T> values) {
        if (isNotNullOrNotEmpty(values)) {
            for (T value : values) {
                addIfNotNull(collection, value);
            }
        }
    }

    public static Integer size(Collection<?> items) {
        return items == null ? 0 : items.size();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static <T> Stream<T> stream(Collection<T> lista) {
        return isNullOrEmpty(lista)
                ? Stream.empty()
                : lista.stream();
    }

    public static <T, U> void removeDuplicates(List<T> lista, Function<T, U> mapper) {
        HashSet<U> existing = new HashSet<>(lista.size());
        lista.removeIf(item -> !existing.add(mapper.apply(item)));
    }

    public static <T, U> boolean hasDuplicates(List<T> lista, Function<T, U> mapper) {
        HashSet<U> existing = new HashSet<>(lista.size());
        return stream(lista)
                .anyMatch(item -> !existing.add(mapper.apply(item)));
    }

    public static <T, U> boolean hasValue(List<T> lista, Function<T, U> mapper, Object value) {
        return stream(lista)
                .anyMatch(item -> mapper.apply(item).equals(value));
    }

    public static <T, U> T getDuplicate(List<T> lista, Function<T, U> mapper) {
        HashSet<U> existing = new HashSet<>(lista.size());
        return first(stream(lista)
                .filter(item -> !existing.add(mapper.apply(item)))
                .collect(Collectors.toList()));
    }

    public static <T> T getDistinct(List<T> lista1, List<T> lista2) {
        return stream(lista1)
                .filter(lista2::contains)
                .findFirst()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> list) {
        return list.toArray((T[]) Array.newInstance(list.iterator().next().getClass(), 0));
    }

}
