package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.TanqueController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.TanqueService;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.entity.Tanque;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lucas
 */
@RestController
@RequiredArgsConstructor
public class TanqueControllerImpl
        extends AbstractController<TanqueService>
        implements TanqueController {

    private final TanqueService tanqueService;

    @Override
    public TanqueDTO incluir(Integer loteId, TanqueDTO tanqueDTO) {
        final Tanque tanque = converterDTOParaEntity(tanqueDTO, Tanque.class);

        return converterEntityParaDTO(tanqueService.incluir(loteId, tanque), TanqueDTO.class);
    }

    @Override
    public void editar(Integer pessoaId, Integer loteId, Integer tanqueId, TanqueDTO tanqueDTO) {
        final Tanque tanque = converterDTOParaEntity(tanqueDTO, Tanque.class);

        tanqueService.editar(pessoaId, loteId, tanqueId, tanque);
    }

    @Override
    public List<TanqueDTO> listarTanquesFromLote(Integer pessoaId, Integer loteId, String orderBy) {
        final List<Tanque> tanques = tanqueService.listarFromPessoaAndLote(pessoaId, loteId, orderBy);

        return converterEntityParaDTO(tanques, TanqueDTO.class);
    }

    @Override
    public TanqueDTO encontrarPorId(Integer pessoaId, Integer loteId, Integer tanqueId) {
        return converterEntityParaDTO(tanqueService.encontrarPorId(pessoaId, loteId, tanqueId), TanqueDTO.class);

    }

    @Override
    public void inativarTanque(Integer pessoaId, Integer loteId, Integer tanqueId) {
        tanqueService.inativarTanque(pessoaId, loteId, tanqueId);
    }

}
