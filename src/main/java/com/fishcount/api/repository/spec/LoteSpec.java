package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
import javax.persistence.criteria.Order;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Lucas Martins
 */
public class LoteSpec {

    public static Specification<Lote> loteByDescricao(String descricao) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("descricao"), descricao);
    }

    public static Specification<Lote> loteByUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("usuario"), usuario);
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
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("descricao"), criteriaBuilder.literal("%" + descricao.toLowerCase() + "%"));
    }
}
