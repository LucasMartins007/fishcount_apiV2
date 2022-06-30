package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Lucas Martins
 */
public class UsuarioSpec {

    private static final String FIELD_ID = "id";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_ATIVO = "ativo";

    UsuarioSpec() {
    }

    public static Specification<Usuario> usuarioAtivo() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FIELD_ATIVO));
    }

    public static Specification<Usuario> usuarioByEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_EMAIL), email);
    }

    public static Specification<Usuario> usuarioById(Integer id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_ID), id);
    }
}
