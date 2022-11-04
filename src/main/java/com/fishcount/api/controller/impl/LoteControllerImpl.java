package com.fishcount.api.controller.impl;


import com.fishcount.api.controller.LoteController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.LoteService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
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

    private final PessoaService pessoaService;

    @Override
    public LoteDTO incluir(Integer pessoaId, LoteDTO loteDTO) {
        Lote lote = converterDTOParaEntity(loteDTO, Lote.class);

        final Pessoa pessoa = pessoaService.findAndValidate(pessoaId);
        return converterEntityParaDTO(loteService.incluir(pessoa, lote), LoteDTO.class);
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

        final Pessoa pessoa = pessoaService.findAndValidate(pessoaId);
        loteService.editar(pessoa, loteId, lote);
    }

    @Override
    public void inativar(Integer pessoaId, Integer loteId) {
        final Lote lote = loteService.findAndValidate(loteId);
        loteService.inativar(pessoaId, lote);
    }

}
