package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CobrancaPixSpec {

    private static final String FIELD_PAGAMENTO_PARCELA = "pagamentoParcela";
    private static final String FIELD_ID = "id";

    public static Specification<CobrancaPix> byPagamentoParcela(Integer idParcela) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_PAGAMENTO_PARCELA).get(FIELD_ID), idParcela);
    }
}
