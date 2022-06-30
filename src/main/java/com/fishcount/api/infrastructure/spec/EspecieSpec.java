package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Especie;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EspecieSpec {

    private static final String FIELD_DESCRICAO = "descricao";

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
