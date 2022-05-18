package com.fishcount.api.validators.pattern;

import com.fishcount.common.model.pattern.IIdentifier;

/**
 *
 * @author lucas
 * @param <E>
 */
public interface IValidator<E extends IIdentifier<?>> {

    void validateRequiredFields(E entity);

    default void validateDelete(E entity) {
    }

    default void validateUpdate(E entity) {
    }

    default void validateInsert(E entity) {
    }

    default void validateInsertOrUpdate(E entity) {
    }

    default void validateSizeFields(E entity) {
    }

}
