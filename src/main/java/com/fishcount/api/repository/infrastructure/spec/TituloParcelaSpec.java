
package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.TituloParcela;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TituloParcelaSpec {
    
    private static final String FIELD_PAGAMENTO_PARCELA = "pagamentoParcela";

    public static Specification<TituloParcela> parcelaByParcelaPagamento(PagamentoParcela pagamentoParcela){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_PAGAMENTO_PARCELA), pagamentoParcela);
    }
}
