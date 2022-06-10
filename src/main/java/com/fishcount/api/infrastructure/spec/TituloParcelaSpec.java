
package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.PagamentoParcela;
import com.fishcount.common.model.entity.TituloParcela;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class TituloParcelaSpec {
    
    private static final String FIELD_PAGAMENTO_PARCELA = "pagamentoParcela";

    public static Specification<TituloParcela> parcelaByParcelaPagamento(PagamentoParcela pagamentoParcela){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_PAGAMENTO_PARCELA), pagamentoParcela);
    }
}
