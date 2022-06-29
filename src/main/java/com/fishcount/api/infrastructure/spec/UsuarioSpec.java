package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.utils.ListUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public class UsuarioSpec {

    private static final String FIELD_ID = "id";
    private static final String FIELD_EMAILS = "emails";
    private static final String FIELD_TIPO_EMAIL = "tipoEmail";
    private static final String FIELD_ATIVO = "ativo";
    private static final String FIELD_DESCRICAO = "descricao";

    UsuarioSpec() {
    }

    public static Specification<Usuario> usuarioAtivo() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FIELD_ATIVO));
    }

    public static Specification<Usuario> usuarioByEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(FIELD_EMAILS).get(FIELD_DESCRICAO),
                email);
    }

    public static Specification<Usuario> usuarioByTipo(EnumTipoEmail tipoEmail) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(FIELD_EMAILS).get(FIELD_TIPO_EMAIL),
                tipoEmail);
    }

    public static Specification<Usuario> usuarioByEmailAndTipo(String email, EnumTipoEmail tipoEmail) {
        return (root, query, criteriaBuilder) -> {
            Join<Usuario, Email> joinUsuarioEmail = root.join(FIELD_EMAILS);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(joinUsuarioEmail.get(FIELD_DESCRICAO), email));
            predicates.add(criteriaBuilder.equal(joinUsuarioEmail.get(FIELD_TIPO_EMAIL), tipoEmail));
            predicates.add(criteriaBuilder.equal(joinUsuarioEmail.get(FIELD_ATIVO), true));

            return query.where(ListUtil.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<Usuario> usuarioById(Integer id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_ID), id);
    }
}
