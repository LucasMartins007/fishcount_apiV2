package com.fishcount.api.controller;


import com.fishcount.api.controller.interfaces.ILoteController;
import com.fishcount.api.service.LoteService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
public class LoteController extends AbstractController<LoteService> implements ILoteController {

    @Override
    public LoteDTO incluir(Integer idPessoa, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);
        
        return converterEntityParaDTO(getService().incluir(idPessoa, lote), LoteDTO.class);
    }

    @Override
    public List<LoteDTO> listarLotesFromUsuario(Integer idPessoa) {
        return converterEntityParaDTO(getService().listarFromPessoa(idPessoa), LoteDTO.class);
    }

    @Override
    public void atualizar(Integer idPessoa, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);
        
        getService().editar(idPessoa, lote);
    }
    
}
