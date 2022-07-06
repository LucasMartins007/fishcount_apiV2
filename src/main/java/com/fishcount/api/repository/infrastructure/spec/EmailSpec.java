package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.enums.EnumTipoEmail;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailSpec {

    private static final String FIELD_DESCRICAO = "descricao";
    private static final String FIELD_ATIVO = "ativo";
    private static final String FIELD_PESSOA = "pessoa";
    private static final String FIELD_TIPO_EMAIL = "tipoEmail";

    public static Specification<Email> emailByDescricao(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_DESCRICAO), descricao);
    }

    public static Specification<Email> emailAtivo() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FIELD_ATIVO));
    }

    public static Specification<Email> emailFromTipo(EnumTipoEmail tipoEmail) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_TIPO_EMAIL), tipoEmail);
    }

    public static Specification<Email> emailFromPessoa(Pessoa pessoa) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_PESSOA), pessoa);
    }

}
