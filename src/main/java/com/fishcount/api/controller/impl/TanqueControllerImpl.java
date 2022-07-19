package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.TanqueService;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.entity.Tanque;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
public class TanqueControllerImpl extends AbstractController<TanqueService> implements com.fishcount.api.controller.TanqueController {

    @Override
    public TanqueDTO incluir(Integer loteId, TanqueDTO tanqueDTO) {
        Tanque tanque = converterDTOParaEntity(tanqueDTO, Tanque.class);
        
       return converterEntityParaDTO(getService().incluir(loteId, tanque), TanqueDTO.class);
    }

    @Override
    public List<TanqueDTO> listarTanquesFromLote(Integer loteId) {
        List<Tanque> tanques = getService().listarFromLote(loteId);
        
        return converterEntityParaDTO(tanques, TanqueDTO.class);
    }
    
}
