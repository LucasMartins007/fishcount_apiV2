package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Titulo;
import com.fishcount.common.model.enums.EnumStatusTitulo;

/**
 *
 * @author Lucas Martins
 */
public interface CustomTituloRepository {

    Titulo findByStatusAndUsuario(EnumStatusTitulo statusTitulo, Pessoa pessoa);
}
