package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoEmail;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class EmailSpec {

    public static Specification<Email> emailByDescricao(String descricao) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("descricao"), descricao);
    }

    public static Specification<Email> emailAtivo() {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.isTrue(root.get("ativo"));
    }

    public static Specification<Email> emailFromTipo(EnumTipoEmail tipoEmail) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("tipoEmail"), tipoEmail);
    }

    public static Specification<Email> emailFromUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("usuario"), usuario);
    }

}
