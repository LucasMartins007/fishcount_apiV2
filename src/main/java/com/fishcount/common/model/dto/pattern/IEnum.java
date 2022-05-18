package com.fishcount.common.model.dto.pattern;

/**
 *
 * @author lucas
 * @param <E>
 */
public interface IEnum<E> {

    E getKey();

    String getValue();

    default String getName() {
        return ((Enum) this).name();
    }

}
