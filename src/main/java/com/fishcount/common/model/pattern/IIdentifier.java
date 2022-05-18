package com.fishcount.common.model.pattern;

import java.io.Serializable;

/**
 *
 * @author lucas
 * @param <T>
 */
public interface IIdentifier<T extends Number> extends Serializable{
    
    T getId();
}
