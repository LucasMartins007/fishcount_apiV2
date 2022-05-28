
package com.fishcount.common.model.enums;

import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.dto.pattern.converter.AbstractEnumConverter;
import lombok.Getter;

import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
@Getter
public enum EnumUnidadeTamanho implements IEnum<String> {

    CENTIMETROS("CM", "Centímetros"),
    MILIMETROS("MM", "Milimeros"),
    METROS("M", "Metros"),
    ;
    
    
    private final String key;
            
    private final String value;
    
    private EnumUnidadeTamanho(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumUnidadeTamanho, String> {
    }
}