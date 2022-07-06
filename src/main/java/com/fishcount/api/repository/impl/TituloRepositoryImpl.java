package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.infrastructure.spec.TituloSpec;
import com.fishcount.api.repository.custom.CustomTituloRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Titulo;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import org.springframework.stereotype.Repository;

@Repository
public class TituloRepositoryImpl extends GenericImpl<Titulo, Integer> implements CustomTituloRepository {

    @Override
    public Titulo findByStatusAndUsuario(EnumStatusTitulo statusTitulo, Pessoa pessoa) {
        return getSpecRepository().findOne(
                TituloSpec.tituloByStatus(statusTitulo)
                        .and(TituloSpec.byPessoa(pessoa)))
                .orElse(null);
    }

}
