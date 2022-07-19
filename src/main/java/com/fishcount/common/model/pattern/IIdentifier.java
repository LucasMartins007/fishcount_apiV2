package com.fishcount.common.model.pattern;

import java.io.Serializable;

/**
 * @param <T>
 * @author lucas
 */
public interface IIdentifier<T extends Number> extends Serializable {

    T getId();
}
