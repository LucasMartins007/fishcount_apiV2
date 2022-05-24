package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author lucas
 */
@Service
public interface LoteService extends IAbstractService<Lote, Integer, LoteDTO> {

    Lote incluir(Integer idUsuario, Lote lote);
    
    List<Lote> listarFromUsuario(Integer idUsuario);

    public void editar(Integer idUsuario, Lote lote);

}
