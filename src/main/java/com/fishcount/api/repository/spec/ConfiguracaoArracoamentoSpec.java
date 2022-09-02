package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.ConfiguracaoArracoamento;
import com.fishcount.common.utils.ListUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracaoArracoamentoSpec {

    final static String FIELD_PESO_MAXIMO = "pesoMaximo";

    final static String FIELD_PESO_MINIMO = "pesoMinimo";

    public static Specification<ConfiguracaoArracoamento> byPeso(BigDecimal pesoPeixe) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(FIELD_PESO_MINIMO), pesoPeixe));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_PESO_MAXIMO), pesoPeixe));

            return query.where(ListUtil.toArray(predicates)).getRestriction();
        };
    }
}
