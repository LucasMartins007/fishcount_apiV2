package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Tanque;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TanqueSpec {

    private static final String PATH_ID = "id";
    private static final String PATH_PESSOA = "pessoa";
    private static final String PATH_LOTE = "lote";

    private static final String PATH_ATIVO = "ativo";


    public static Specification<Tanque> byId(Integer tanqueId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(PATH_ID), tanqueId);
    }

    public static Specification<Tanque> byAtivo() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get(PATH_ATIVO));
    }

    public static Specification<Tanque> byPessoa(Integer pessoaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(PATH_LOTE).get(PATH_PESSOA).get(PATH_ID), pessoaId);
    }

    public static Specification<Tanque> byLote(Integer loteId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(PATH_LOTE).get(PATH_ID), loteId);
    }

    public static Specification<Tanque> orderBy(boolean isAscending, String campo) {
        return (root, query, criteriaBuilder) -> {
            final Order order = isAscending ? criteriaBuilder.asc(root.get(campo)) : criteriaBuilder.desc(root.get(campo));

            return query
                    .distinct(true)
                    .orderBy(order).getRestriction();
        };
    }

}
