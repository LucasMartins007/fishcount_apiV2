package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.financeiro.Plano;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

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
}
