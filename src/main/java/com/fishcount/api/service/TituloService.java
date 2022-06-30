package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.financeiro.TituloDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.entity.financeiro.Titulo;

/**
 *
 * @author Lucas Martins
 */
public interface TituloService extends IAbstractService<Titulo, Integer, TituloDTO> {
    
    Titulo gerarTituloByPlano(Pessoa pessoa, Plano plano);

}
