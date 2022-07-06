package com.fishcount.api.repository.infrastructure.spec;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Titulo;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Lucas Martins
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TituloSpec {

    private static final String FIELD_PESSOA = "pessoa";

    private static final String FIELD_STATUS_TITULO = "statusTitulo";

    public static Specification<Titulo> tituloByStatus(EnumStatusTitulo statusTitulo) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_STATUS_TITULO), statusTitulo);
    }

    public static Specification<Titulo> byPessoa(Pessoa pessoa) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(FIELD_PESSOA), pessoa);
    }
}
