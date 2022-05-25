package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Especie;
import javax.persistence.criteria.Order;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class EspecieSpec {

    static public Specification<Especie> findByDescricao(String descricao) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("descricao"), descricao);
    }

    static public Specification<Especie> orderBy(boolean isAscending, String campo) {
        return (root, query, criteriaBuilder) -> {
            Order orderBy = isAscending ? criteriaBuilder.asc(root.get(campo)) : criteriaBuilder.desc(root.get(campo));

            return query
                    .distinct(true)
                    .orderBy(orderBy)
                    .getRestriction();
        };
    }
}
