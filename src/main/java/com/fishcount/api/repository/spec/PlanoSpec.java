package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.financeiro.Plano;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanoSpec {

    private static final String FIELD_ATIVO = "ativo";

    public static Specification<Plano> byAtivoTrue() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FIELD_ATIVO));
    }

    public static Specification<Plano> orderBy(boolean isAscending, String campo) {
        return (root, query, criteriaBuilder) -> {
            Order order = isAscending ? criteriaBuilder.asc(root.get(campo)) : criteriaBuilder.desc(root.get(campo));

            return query
                    .distinct(true)
                    .orderBy(order).getRestriction();
        };
    }
}
