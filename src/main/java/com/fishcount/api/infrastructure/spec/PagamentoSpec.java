package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public class PagamentoSpec {

    private PagamentoSpec() {
    }

    private static final String FIELD_PESSOA = "pessoa";
    private static final String FIELD_STATUS = "statusPagamento";
    private static final String FIELD_ID = "id";

    public static Specification<Pagamento> byPessoa(Pessoa pessoa) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_PESSOA), pessoa);
    }

    public static Specification<Pagamento> byId(Integer id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_ID), id);
    }

    public static Specification<Pagamento> byStatus(List<EnumStatusPagamento> status){
        return (root, query, criteriaBuilder)
                -> root.get(FIELD_STATUS).in(status);
    }
}
