package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class TaxaCrescimentoSpec {

    private static final String FIELD_ESPECIE = "especie";

    TaxaCrescimentoSpec() {
    }

    public static Specification<TaxaCrescimento> taxaByEspecie(Especie especie) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_ESPECIE), especie);
    }
}
