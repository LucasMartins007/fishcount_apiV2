package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;

import java.util.List;

/**
 *
 * @author lucas
 */
public interface LoteService extends IAbstractService<Lote, Integer, LoteDTO> {

    Lote incluir(Pessoa pessoa, Lote lote);
    
    List<Lote> listarFromPessoa(Integer pessoaId, String orderBy);

    void editar(Pessoa pessoa, Integer loteId, Lote lote);

    void onPrepareInsertOrUpdate(Pessoa pessoa, Integer loteId, Lote lote);

    void inativar(Integer pessoaId, Lote lote);

    void validarLotes(Pessoa pessoa);
}
