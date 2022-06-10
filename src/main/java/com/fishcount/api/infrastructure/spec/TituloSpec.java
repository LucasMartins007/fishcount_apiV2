package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class TituloSpec {

    public static Specification<Titulo> tituloByStatus(EnumStatusTitulo statusTitulo) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("statusTitulo"), statusTitulo);
    }

    public static Specification<Titulo> tituloByUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("usuario"), usuario);
    }
}
