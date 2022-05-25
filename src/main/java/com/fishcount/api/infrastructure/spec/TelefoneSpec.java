package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoTelefone;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class TelefoneSpec {

    private static final String FIELD_DESCRICAO = "descricao";
    private static final String FIELD_ATIVO = "ativo";
    private static final String FIELD_USUARIO = "usuario";
    private static final String FIELD_TIPO_TELEFONE = "tipoTelefone";

    TelefoneSpec() {
    }

    public static Specification<Telefone> telefoneByDescricao(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_DESCRICAO), descricao);
    }

    public static Specification<Telefone> telefonesAtivos() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(FIELD_ATIVO));
    }

    public static Specification<Telefone> telefonesByUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_USUARIO), usuario);
    }

    public static Specification<Telefone> telefoneByTipo(EnumTipoTelefone tipoTelefone) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_TIPO_TELEFONE), tipoTelefone);
    }

}
