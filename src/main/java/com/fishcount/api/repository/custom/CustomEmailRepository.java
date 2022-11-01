package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Email;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomEmailRepository {

    Email findAtivoByDescricao(String descricao);

    List<Email> findAllByPessoa(Integer pessoaId);
    
}
