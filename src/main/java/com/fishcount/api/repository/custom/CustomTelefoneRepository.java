package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.enums.EnumTipoTelefone;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomTelefoneRepository {

    Telefone findByDescricao(String descricao);
    
    Telefone findByPessoaAndTipo(Pessoa pessoa, EnumTipoTelefone tipoTelefone);
    
    List<Telefone> findAllAtivosByPessoa(Pessoa pessoa);
}
