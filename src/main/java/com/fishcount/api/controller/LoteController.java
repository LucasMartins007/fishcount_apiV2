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
    public LoteDTO incluir(Integer pessoaId, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);
        
        return converterEntityParaDTO(getService().incluir(pessoaId, lote), LoteDTO.class);
    }

    @Override
    public List<LoteDTO> listar(Integer pessoaId) {
        return converterEntityParaDTO(getService().listarFromPessoa(pessoaId), LoteDTO.class);
    }

    @Override
    public void atualizar(Integer pessoaId, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);
        
        getService().editar(pessoaId, lote);
    }
    
}
