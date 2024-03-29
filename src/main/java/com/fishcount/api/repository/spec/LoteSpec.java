package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Lote;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;

/**
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoteSpec {

    private static final String FIELD_ID = "id";

    private static final String FIELD_DESCRICAO = "descricao";

    private static final String FIELD_PESSOA = "pessoa";

    private static final String FIELD_ATIVO = "ativo";

    public static Specification<Lote> byDescricao(String descricao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(FIELD_DESCRICAO), descricao);
    }

    public static Specification<Lote> byPessoaId(Integer pessoaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(FIELD_PESSOA).get(FIELD_ID), pessoaId);
    }

    public static Specification<Lote> byAtivo() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get(FIELD_ATIVO));
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
