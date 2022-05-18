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
public enum EnumTipoTitulo implements IEnum<Integer> {

    ENTRADA(1, "Entrada"),
    SAIDA(2, "Saida"),
    ESTORNO(3, "Estorno");

    private final Integer key;

    private final String value;

    private EnumTipoTitulo(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoTitulo, Integer> {
    }

}
