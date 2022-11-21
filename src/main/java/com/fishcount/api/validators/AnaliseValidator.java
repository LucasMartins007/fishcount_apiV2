package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.IValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.utils.Utils;

import java.math.BigDecimal;

public class AnaliseValidator implements IValidator<Analise> {
    @Override
    public void validateRequiredFields(Analise analise) {

    }

    public static void validarPesoMaximoEMinimo(BigDecimal pesoAtual, EnumUnidadePeso unidadePeso) {
        if ((pesoAtual.doubleValue() < 10 && EnumUnidadePeso.GRAMA.equals(unidadePeso))
                || (pesoAtual.doubleValue() < 0.1 && EnumUnidadePeso.KILO.equals(unidadePeso))) {
            throw new FcRuntimeException(EnumFcDomainException.PESO_UNITARIO_ABAIXO_LIMITE, pesoAtual, unidadePeso.getDescricao());
        }

        if ((pesoAtual.doubleValue() > 700 && EnumUnidadePeso.GRAMA.equals(unidadePeso))
                || (pesoAtual.doubleValue() > 0.7 && EnumUnidadePeso.KILO.equals(unidadePeso))) {
            throw new FcRuntimeException(EnumFcDomainException.PESO_UNITARIO_ACIMA_LIMITE, pesoAtual, unidadePeso.getDescricao());
        }
    }

    public static void validarTemperatura(BigDecimal temperatura) {
        if (Utils.isNotEmpty(temperatura) && (temperatura.doubleValue() > 30 || temperatura.doubleValue() < 18)) {
            throw new FcRuntimeException(EnumFcDomainException.TEMPERATURA_AGUA_NAO_PERMITIDA);
        }
    }

    public static void validarQuantidadePeixes(Integer qtdePeixes) {
        if (Utils.isNotEmpty(qtdePeixes) && qtdePeixes.doubleValue() >= 10000.0) {
            throw new FcRuntimeException(EnumFcDomainException.QTDE_PEIXES_ACIMA_LIMITE, qtdePeixes);
        }
    }
}
