package com.fishcount.api.controller.impl;


import com.fishcount.api.controller.LoteController;
import com.fishcount.api.controller.pattern.AbstractController;
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
public class LoteControllerImpl extends AbstractController<LoteService> implements LoteController {

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
    public void atualizar(Integer pessoaId, Integer loteId, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);
        
        getService().editar(pessoaId, loteId,lote);
    }

    @Override
    public void inativar(Integer pessoaId, Integer loteId) {
        getService().inativar(pessoaId, loteId);
    }

}
