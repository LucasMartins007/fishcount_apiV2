package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.model.enums.EnumUnidadeTamanho;

import java.math.BigDecimal;

public class EspecieMock {

    public static Especie criarMock() {
        final Especie especie = new Especie();
        especie.setId(1);
        especie.setDescricao("Tilápia");
        especie.setAtivo(true);

        especie.setPesoMedio(BigDecimal.valueOf(10));
        especie.setUnidadePesoMedio(EnumUnidadePeso.GRAMA);

        especie.setTamanhoMedio(BigDecimal.valueOf(10));
        especie.setUnidadeTamanho(EnumUnidadeTamanho.CENTIMETROS);

        return especie;
    }
}
