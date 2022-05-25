package com.fishcount.common.model.dto.pattern;

import com.fishcount.common.model.pattern.IIdentifier;

import lombok.Data;

/**
 *
 * @author lucas
 * @param <T>
 */
@Data
public abstract class AbstractDTO<T extends Number> implements IIdentifier<T> {
    
}
