package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.entity.Tanque;

import java.util.List;

/**
 *
 * @author lucas
 */
public interface TanqueService extends IAbstractService<Tanque, Integer, TanqueDTO> {
    
    Tanque incluir(Integer loteId, Tanque tanque);
    
    List<Tanque> listarFromLote(Integer loteId);
    
    
}