package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomTelefoneRepository;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lucas
 */
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer>, JpaSpecificationExecutor<Telefone>, CustomTelefoneRepository {

    @Override
    Telefone findByDescricao(String descricao);

    @Override
    Telefone findByPessoaAndTipo(Pessoa pessoa, EnumTipoTelefone tipoTelefone);
    
    @Override
    List<Telefone> findAllAtivosByPessoa(Pessoa pessoa);
    
}
