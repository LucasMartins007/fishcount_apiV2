package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.ListUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PagamentoParcelaSpec {

    private static final String FIELD_PESSOA = "pessoa";
    private static final String FIELD_PAGAMENTO = "pagamento";
    private static final String FIELD_STATUS_PAGAMENTO = "statusPagamento";
    private static final String FIELD_ID = "id";

    public static Specification<PagamentoParcela> byUsuario(final Integer pessoaId) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_PESSOA).get(FIELD_ID), pessoaId);
    }

    public static Specification<PagamentoParcela> byStatus(final EnumStatusPagamento statusPagamento) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_STATUS_PAGAMENTO), statusPagamento);
    }

    public static Specification<PagamentoParcela> byPagamento(final Integer pagamentoId) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_ID), pagamentoId);
    }

    public static Specification<PagamentoParcela> byUsuarioAndPagamentoAndStatus(final Integer pessoaId, final Integer pagamentoId, final EnumStatusPagamento statusPagamento) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_PESSOA).get(FIELD_ID), pessoaId));
            predicates.add(criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_ID), pagamentoId));
            predicates.add(criteriaBuilder.equal(root.get(FIELD_STATUS_PAGAMENTO), statusPagamento));

            return query.where(ListUtil.toArray(predicates)).getRestriction();
        };
    }
}
