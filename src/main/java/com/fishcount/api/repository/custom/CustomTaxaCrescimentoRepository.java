package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;

/**
 *
 * @author Lucas Martins
 */
public interface CustomTaxaCrescimentoRepository {

    TaxaCrescimento findByEspecie(Especie especie);
}
