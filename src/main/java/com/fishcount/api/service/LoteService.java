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

    Lote incluir(Integer idUsuario, Lote lote);
    
    List<Lote> listarFromPessoa(Integer idUsuario);

    void editar(Integer idUsuario, Lote lote);

    void onPrepareInsertOrUpdate(Integer idPessoa, Lote lote);
}
