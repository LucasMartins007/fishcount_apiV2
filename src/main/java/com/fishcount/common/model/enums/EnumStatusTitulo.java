package com.fishcount.common.model.enums;

import com.fishcount.common.model.enums.pattern.IEnum;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import lombok.Getter;

import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
@Getter
public enum EnumStatusTitulo implements IEnum<Integer> {

    ANALISE(0, "Em Análise"),
    ABERTO(1, "Em aberto"),
    LIQUIDADO(2, "Liquidado"),
    LIQUIDADO_PARCIALMENTE(3, "Liquidado parcialmente"),
    CANCELADO(4, "Cancelado");

    private final Integer key;

    private final String value;

    private EnumStatusTitulo(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumStatusTitulo, Integer> {
    }

}
