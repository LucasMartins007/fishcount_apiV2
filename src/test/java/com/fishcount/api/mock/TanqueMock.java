package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.utils.DateUtil;
import org.checkerframework.checker.units.qual.A;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;

public class TanqueMock {

    public static Tanque criarMock() {
        final Tanque tanque = new Tanque();

        tanque.setId(1);
        tanque.setQtdUltimaAnalise(1200);
        tanque.setPesoUnitario(BigDecimal.valueOf(350L));
        tanque.setUnidadePeso(EnumUnidadePeso.GRAMA);
        tanque.setQtdePeixe(1000);
        tanque.setAtivo(true);
        tanque.setStatusAnalise(EnumStatusAnalise.FALHA_ANALISE);
        tanque.setDataUltimoTratamento(DateUtil.subtract(DateUtil.getDate(), Calendar.DAY_OF_WEEK, 2));
        tanque.setDataUltimaAnalise(DateUtil.subtract(DateUtil.getDate(), Calendar.DAY_OF_WEEK, 1));
        tanque.setDataInclusao(DateUtil.getDate());
        tanque.setDataAtualizacao(DateUtil.getDate());
        tanque.setPossuiMedicaoTemperatura(true);
        tanque.setEspecie(EspecieMock.criarMock());
        tanque.setAnalises(Collections.singletonList(new Analise()));

        return tanque;
    }
}
