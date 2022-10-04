package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Lote;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomLoteRepository {

    Lote findAtivoByDescricao(String descricao);

    List<Lote> findAllAtivosByPessoa(Integer pessoaId);

    List<Lote> findAllAtivosByPessoaOrderBy(Integer pessoaId, String field);
}
