package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface PlanoService extends IAbstractService<Plano, Integer, PlanoDTO> {

    List<Plano> listarPlanos();

    Plano incluir(Plano plano);
}
