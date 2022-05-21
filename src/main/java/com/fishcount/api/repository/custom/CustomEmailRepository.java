package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomEmailRepository {

    Email findAtivoByDescricao(String descricao);

    List<Email> findAllByUsuario(Usuario usuario);
    
}
