package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class UsuarioSpec {

    public static Specification<Usuario> usuarioAtivo() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("ativo"));
    }

    public static Specification<Usuario> usuarioByEmail(String email) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.join("emails").get("descricao"), email);
    }

    public static Specification<Usuario> usuarioById(Integer id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("id"), id);
    }
}
