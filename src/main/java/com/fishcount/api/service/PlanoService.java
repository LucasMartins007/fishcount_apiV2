package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface PlanoService extends IAbstractService<Plano, Integer, PlanoDTO> {

    List<Plano> listarPlanos();

    Plano incluir(Plano plano);
}
