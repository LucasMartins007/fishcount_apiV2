package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.financeiro.TituloDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.entity.financeiro.Titulo;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface TituloService extends IAbstractService<Titulo, Integer, TituloDTO> {
    
    Titulo gerarTituloByPlano(Usuario usuario, Plano plano);

}
