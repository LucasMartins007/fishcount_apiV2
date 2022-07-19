package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.AutenticacaoDTO;
import com.fishcount.common.model.entity.Usuario;

public interface LoginService extends IAbstractService<Usuario, Integer, AutenticacaoDTO> {

    AutenticacaoDTO autenticar(AutenticacaoDTO autenticacaoDTO);
}
