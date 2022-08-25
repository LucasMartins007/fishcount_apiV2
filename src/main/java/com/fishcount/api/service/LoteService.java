package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;

import java.util.List;

/**
 *
 * @author lucas
 */
public interface LoteService extends IAbstractService<Lote, Integer, LoteDTO> {

    Lote incluir(Integer pessoaId, Lote lote);
    
    List<Lote> listarFromPessoa(Integer pessoaId, String orderBy);

    void editar(Integer pessoaId, Integer loteId, Lote lote);

    void onPrepareInsertOrUpdate(Integer pessoaId, Integer loteId, Lote lote);

    void inativar(Integer pessoaId, Integer loteId);
}
