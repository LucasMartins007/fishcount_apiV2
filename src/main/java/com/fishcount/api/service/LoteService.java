package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lucas
 */
public interface LoteService extends IAbstractService<Lote, Integer, LoteDTO> {

    Lote incluir(Integer idUsuario, Lote lote);
    
    List<Lote> listarFromUsuario(Integer idUsuario);

    void editar(Integer idUsuario, Lote lote);

}
