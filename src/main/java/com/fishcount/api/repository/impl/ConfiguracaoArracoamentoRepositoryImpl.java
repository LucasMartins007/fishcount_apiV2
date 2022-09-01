package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomConfiguracaoArracoamentoRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.ConfiguracaoArracoamentoSpec;
import com.fishcount.common.model.entity.ConfiguracaoArracoamento;

import java.math.BigDecimal;

public class ConfiguracaoArracoamentoRepositoryImpl
        extends RepositoryImpl<ConfiguracaoArracoamento, Integer> implements CustomConfiguracaoArracoamentoRepository {

    @Override
    public ConfiguracaoArracoamento findByPeso(BigDecimal pesoPeixe) {
        return getSpecRepository()
                .findOne(ConfiguracaoArracoamentoSpec.byPeso(pesoPeixe))
                .orElse(null);
    }
}
