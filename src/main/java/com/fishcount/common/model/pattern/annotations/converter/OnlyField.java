package com.fishcount.common.model.pattern.annotations.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 ** <p> Anotação utilizada para indicar quais propriedades de um
 * campo DTO devem ser utilizados na serialização da resposta. </p>
 * 
 * <p> Caso o campo não exista, a entrada na anotação é ignorada. </p>
 * 
 * @author lucas
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyField {

    String[] value();

}