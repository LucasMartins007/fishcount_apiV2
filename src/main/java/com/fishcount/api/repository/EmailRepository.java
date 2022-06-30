package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomEmailRepository;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lucas
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Integer>, JpaSpecificationExecutor<Email>, CustomEmailRepository {

    @Override
    Email findAtivoByDescricao(String descricao);

    @Override
    List<Email> findAllByPessoa(Pessoa pessoa);
    
}
