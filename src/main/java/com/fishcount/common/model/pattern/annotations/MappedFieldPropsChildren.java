/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fishcount.common.model.pattern.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * O uso @MappedFieldPropsChildren ocorre dentro de uma field DTO.
 * 
 * Mapeamento feito pelo nome das fields da Classe.
 * 
 * @author lucas
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MappedFieldPropsChildren {

    String value();
}
