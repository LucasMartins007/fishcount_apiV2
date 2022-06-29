package com.fishcount.api.infrastructure.spec;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;

/**
 *
 * @author Lucas Martins
 */
public class LoteSpec {

    private static final String FIELD_DESCRICAO = "descricao";
    private static final String FIELD_USUARIO = "usuario";

    LoteSpec() {
    }

    public static Specification<Lote> loteByDescricao(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_DESCRICAO), descricao);
    }

    public static Specification<Lote> loteByUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_USUARIO), usuario);
    }

    public static Specification<Lote> orderBy(boolean isAscending, String campo) {
        return (root, query, criteriaBuilder) -> {
            Order order = isAscending ? criteriaBuilder.asc(root.get(campo)) : criteriaBuilder.desc(root.get(campo));

            return query
                    .distinct(true)
                    .orderBy(order).getRestriction();
        };
    }

    public static Specification<Lote> loteLikeDescricao(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(FIELD_DESCRICAO),
                criteriaBuilder.literal("%" + descricao.toLowerCase() + "%"));
    }
}
