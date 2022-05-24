package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class TaxaCrescimentoSpec {

    static public Specification<TaxaCrescimento> taxaByEspecie(Especie especie) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("especie"), especie);
    }
}
