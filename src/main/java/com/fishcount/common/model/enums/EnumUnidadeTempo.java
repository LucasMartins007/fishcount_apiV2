
package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;
import lombok.Getter;

import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
@Getter
public enum EnumUnidadeTempo implements IEnum<String> {

    DIAS("DIA", "Dias"),
    SEMANAS("SEMANA" ,"Semanas"),
    MESES("MES", "Meses")
    ;
    
    private final String key;
    
    private final String value;
    
    private EnumUnidadeTempo(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumUnidadeTempo, String> {
    }
}