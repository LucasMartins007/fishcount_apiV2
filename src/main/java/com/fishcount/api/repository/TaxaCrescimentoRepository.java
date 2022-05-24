package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomTaxaCrescimentoRepository;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Lucas Martins
 */
public interface TaxaCrescimentoRepository extends JpaRepository<TaxaCrescimento, Integer>, JpaSpecificationExecutor<TaxaCrescimento>, CustomTaxaCrescimentoRepository {

    @Override
    TaxaCrescimento findByEspecie(Especie especie);
    
}
