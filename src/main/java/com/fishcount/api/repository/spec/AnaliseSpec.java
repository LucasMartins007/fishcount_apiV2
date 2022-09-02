package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;

public class AnaliseSpec {
    private static String FIELD_STATUS_ANALISE = "statusAnalise";

    private static String FIELD_TANQUE = "tanque";

    public static Specification<Analise> byStatus(EnumStatusAnalise statusAnalise) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(FIELD_STATUS_ANALISE), statusAnalise);
    }

    public static Specification<Analise> byTanque(Tanque tanque) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(FIELD_TANQUE), tanque);
    }

    public static Specification<Analise> orderBY(boolean isAscending, String campo) {
        return (root, query, criteriaBuilder) -> {
            Order order = isAscending ? criteriaBuilder.asc(root.get(campo)) : criteriaBuilder.desc(root.get(campo));

            return query
                    .distinct(true)
                    .orderBy(order).getRestriction();
        };
    }
}
