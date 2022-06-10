package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.TituloDTO;
import com.fishcount.common.model.entity.Plano;
import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public interface TituloService extends IAbstractService<Titulo, Integer, TituloDTO> {

    Titulo incluir(Integer idUsuario, Titulo titulo);
    
    Titulo gerarTituloByPlano(Usuario usuario, Plano plano);

}
