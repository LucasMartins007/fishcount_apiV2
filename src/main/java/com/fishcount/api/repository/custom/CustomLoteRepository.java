package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomLoteRepository {

    Lote findAtivoByDescricao(String descricao);

    List<Lote> findAllAtivosByPessoa(Pessoa pessoa);
}
