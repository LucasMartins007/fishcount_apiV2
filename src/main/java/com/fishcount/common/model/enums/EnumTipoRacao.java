package com.fishcount.common.model.enums;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.pattern.AbstractEnumConverter;
import com.fishcount.common.model.enums.pattern.IEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

@RequiredArgsConstructor
@Getter
public enum EnumTipoRacao implements IEnum<Integer> {

    RACAO_EM_PO (1, "Ração em pó",42, 42 ),

    DOIS_A_TRES (2, "Ração 2-3 mm", 42,42),

    TRES_A_QUATRO (3,"Ração 3-4 mm", 36,36),

    QUATRO_A_SEIS (4, "Ração de 4-6 mm", 28, 32),

    SEIS_A_OITO (5, "Ração 6-8 mm", 28,32)
    ;

    private final Integer key;

    private final String value;

    private final Integer proteinaBrutaMaxima;

    private final Integer proteinaBrutaMinima;


    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoRacao, Integer> {
    }






}
