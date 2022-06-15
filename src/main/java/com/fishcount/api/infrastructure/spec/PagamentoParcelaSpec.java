package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Lucas Martins
 */
public class PagamentoParcelaSpec {

    private final static String FIELD_USUARIO = "usuario";
    private final static String FIELD_PAGAMENTO = "pagamento";
    private final static String FIELD_STATUS_PAGAMENTO = "statusPagamento";
    private final static String FIELD_ID = "id";

    public static Specification<PagamentoParcela> byUsuario(final Integer idUsuario) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_USUARIO).get(FIELD_ID), idUsuario);
    }

    public static Specification<PagamentoParcela> byStatus(final EnumStatusPagamento statusPagamento) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_STATUS_PAGAMENTO), statusPagamento);
    }

    public static Specification<PagamentoParcela> byPagamento(final Integer idPagamento) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_ID), idPagamento);
    }

    public static Specification<PagamentoParcela> byUsuarioAndPagamentoAndStatus(final Integer idUsuario, final Integer idPagamento, final EnumStatusPagamento statusPagamento) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_USUARIO).get(FIELD_ID), idUsuario));
            predicates.add(criteriaBuilder.equal(root.get(FIELD_PAGAMENTO).get(FIELD_ID), idPagamento));
            predicates.add(criteriaBuilder.equal(root.get(FIELD_STATUS_PAGAMENTO), statusPagamento));

            return query.where(ListUtil.toArray(predicates)).getRestriction();
        };
    }
}
