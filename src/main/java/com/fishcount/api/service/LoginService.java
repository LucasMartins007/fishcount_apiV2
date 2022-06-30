package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.classes.AutenticacaoDTO;
import com.fishcount.common.model.entity.Usuario;

public interface LoginService extends IAbstractService<Usuario, Integer, AutenticacaoDTO> {

    AutenticacaoDTO autenticar(AutenticacaoDTO autenticacaoDTO);
}
