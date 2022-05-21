package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.utils.ListUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
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

    public static Specification<Usuario> usuarioByTipo(EnumTipoEmail tipoEmail) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.join("emails").get("tipoEmail"), tipoEmail);
    }

    public static Specification<Usuario> usuarioByEmailAndTipo(String email, EnumTipoEmail tipoEmail) {
        return (root, query, criteriaBuilder) -> {
            Join<Usuario, Email> joinUsuarioEmail = root.join("emails");

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(joinUsuarioEmail.get("descricao"), email));
            predicates.add(criteriaBuilder.equal(joinUsuarioEmail.get("tipoEmail"), tipoEmail));
            predicates.add(criteriaBuilder.equal(joinUsuarioEmail.get("ativo"), true));

            return query.where(ListUtil.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<Usuario> usuarioById(Integer id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("id"), id);
    }
}
