package com.fishcount.common.model.enums;

import com.fishcount.common.model.dto.pattern.IEnum;
import com.fishcount.common.model.dto.pattern.converter.AbstractEnumConverter;
import com.fishcount.common.model.entity.Email;
import lombok.Getter;

import javax.persistence.Converter;

/**
 * @author lucas
 */
@Getter
public enum EnumTipoEmail implements IEnum<Integer> {

    PRINCIPAL(1, "Principal"),
    ADICIONAL(2, "Adicional"),
    COMERCIAL(3, "Comercial"),
    ;

    private EnumTipoEmail(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    private final Integer key;

    private final String value;

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoEmail, Integer> {
    }

    public static boolean isPrincipal(Email email) {
        return PRINCIPAL.equals(email.getTipoEmail());
    }

    public static boolean isComercial(Email email) {
        return COMERCIAL.equals(email.getTipoEmail());
    }

    public static boolean isAdicional(Email email) {
        return ADICIONAL.equals(email.getTipoEmail());
    }

}
