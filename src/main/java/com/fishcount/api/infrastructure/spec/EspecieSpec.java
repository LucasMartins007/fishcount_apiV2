package com.fishcount.api.infrastructure.spec;

import javax.persistence.criteria.Order;

import com.fishcount.common.model.entity.Especie;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class EspecieSpec {

    private static final String FIELD_DESCRICAO = "descricao";

    EspecieSpec() {
    }

    public static Specification<Especie> findByDescricao(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_DESCRICAO), descricao);
    }

    public static Specification<Especie> orderBy(boolean isAscending, String campo) {
        return (root, query, criteriaBuilder) -> {
            Order orderBy = isAscending ? criteriaBuilder.asc(root.get(campo)) : criteriaBuilder.desc(root.get(campo));

            return query
                    .distinct(true)
                    .orderBy(orderBy)
                    .getRestriction();
        };
    }
}
