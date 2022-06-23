package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public class PagamentoSpec {

    private final static String FIELD_USUARIO = "usuario";
    private final static String FIELD_STATUS = "statusPagamento";
    private final static String FIELD_ID = "id";

    public static Specification<Pagamento> byUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_USUARIO), usuario);
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
