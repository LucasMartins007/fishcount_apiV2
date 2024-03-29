package com.fishcount.common.model.enums.pattern;

/**
 *
 * @author lucas
 * @param <E>
 */
public interface IEnum<E> {

    E getKey();

    String getValue();

    @SuppressWarnings("rawtypes")
    default String getName() {
        return ((Enum) this).name();
    }

}
