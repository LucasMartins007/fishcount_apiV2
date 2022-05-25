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

    public static Specification<Telefone> telefoneByDescricao(String descricao) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("descricao"), descricao);
    }

    public static Specification<Telefone> telefonesAtivos() {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.isTrue(root.get("ativo"));
    }

    public static Specification<Telefone> telefonesByUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("usuario"), usuario);
    }

    public static Specification<Telefone> telefoneByTipo(EnumTipoTelefone tipoTelefone) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("tipoTelefone"), tipoTelefone);
    }

}
