package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

/**
 * @author lucas
 */
@Getter
@RequiredArgsConstructor
public enum EnumTipoEmail implements IEnum<Integer> {

    PRINCIPAL(1, "Principal"),
    ADICIONAL(2, "Adicional"),
    COMERCIAL(3, "Comercial"),
    ;



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
