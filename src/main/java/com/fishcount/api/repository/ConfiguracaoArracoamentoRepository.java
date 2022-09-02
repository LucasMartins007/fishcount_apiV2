package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomConfiguracaoArracoamentoRepository;
import com.fishcount.common.model.entity.ConfiguracaoArracoamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ConfiguracaoArracoamentoRepository
        extends JpaRepository<ConfiguracaoArracoamento, Integer>, JpaSpecificationExecutor<ConfiguracaoArracoamento>, CustomConfiguracaoArracoamentoRepository {

    @Override
    ConfiguracaoArracoamento findByPeso(BigDecimal pesoPeixe);

}
