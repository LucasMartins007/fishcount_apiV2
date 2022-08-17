package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Tanque;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TanqueSpec {

    private static final String PATH_ID = "id";
    private static final String PATH_PESSOA = "pessoa";
    private static final String PATH_LOTE = "lote";

    public static Specification<Tanque> byId(Integer tanqueId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(PATH_ID), tanqueId);
    }

    public static Specification<Tanque> byPessoa(Integer pessoaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(PATH_LOTE).get(PATH_PESSOA).get(PATH_ID), pessoaId);
    }

    public static Specification<Tanque> byLote(Integer loteId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(PATH_LOTE).get(PATH_ID), loteId);
    }

}
