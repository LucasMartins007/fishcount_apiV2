package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.ConfiguracaoArracoamento;

import java.math.BigDecimal;

public interface CustomConfiguracaoArracoamentoRepository {

    ConfiguracaoArracoamento findByPeso(BigDecimal pesoPeixe);

}
