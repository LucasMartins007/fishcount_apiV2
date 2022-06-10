package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Pagamento;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class PagamentoSpec {

    private final static String FIELD_USUARIO = "usuario";
    private final static String FIELD_ID = "id";

    public static Specification<Pagamento> pagamentosByUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_USUARIO), usuario);
    }

    public static Specification<Pagamento> pagamentoById(Integer id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_ID), id);
    }
}
