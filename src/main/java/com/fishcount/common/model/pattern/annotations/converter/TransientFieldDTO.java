package com.fishcount.common.model.pattern.annotations.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indica que o campo anotado na DTO é transiente e deve
 * ser ignorado na serialização.
 * 
 * @author lucas
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransientFieldDTO {
}

