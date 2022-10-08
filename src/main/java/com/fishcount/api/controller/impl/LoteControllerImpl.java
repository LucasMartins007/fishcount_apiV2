package com.fishcount.api.controller.impl;


import com.fishcount.api.controller.LoteController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.LoteService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.utils.ListUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author lucas
 */
@RestController
@RequiredArgsConstructor
public class LoteControllerImpl extends AbstractController<LoteService> implements LoteController {

    private final LoteService loteService;

    @Override
    public LoteDTO incluir(Integer pessoaId, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);

        return converterEntityParaDTO(loteService.incluir(pessoaId, lote), LoteDTO.class);
    }

    @Override
    public List<LoteDTO> listar(Integer pessoaId, String orderBy) {
        final List<Lote> lotes = loteService.listarFromPessoa(pessoaId, orderBy);
        if (ListUtil.isNullOrEmpty(lotes)) {
            return Collections.emptyList();
        }

        return converterEntityParaDTO(lotes, LoteDTO.class);
    }

    @Override
    public LoteDTO encontrarPorId(Integer pessoaId, Integer loteId) {
        return converterEntityParaDTO(loteService.findAndValidate(loteId), LoteDTO.class);
    }

    @Override
    public void atualizar(Integer pessoaId, Integer loteId, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);

        loteService.editar(pessoaId, loteId, lote);
    }

    @Override
    public void inativar(Integer pessoaId, Integer loteId) {
        loteService.inativar(pessoaId, loteId);
    }

}
