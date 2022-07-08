package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoteSpec {

    private static final String FIELD_DESCRICAO = "descricao";
    private static final String FIELD_PESSOA = "pessoa";

    public static Specification<Lote> byDescricao(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_DESCRICAO), descricao);
    }

    public static Specification<Lote> byPessoa(Pessoa pessoa) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_PESSOA), pessoa);
    }

    public static Specification<Lote> orderBy(boolean isAscending, String campo) {
        return (root, query, criteriaBuilder) -> {
            Order order = isAscending ? criteriaBuilder.asc(root.get(campo)) : criteriaBuilder.desc(root.get(campo));

            return query
                    .distinct(true)
                    .orderBy(order).getRestriction();
        };
    }

    public static Specification<Lote> likeDescricao(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(FIELD_DESCRICAO),
                criteriaBuilder.literal("%" + descricao.toLowerCase() + "%"));
    }
}
