package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomEmailRepository;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author lucas
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Integer>, JpaSpecificationExecutor<Email>, CustomEmailRepository {

    @Override
    public Email findAtivoByDescricao(String descricao);

    @Override
    public List<Email> findAllByUsuario(Usuario usuario);
    
}
