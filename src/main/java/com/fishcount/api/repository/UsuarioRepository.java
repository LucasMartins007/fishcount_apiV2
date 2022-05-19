package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fishcount.api.repository.custom.CustomUsuarioRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author lucas
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario>, CustomUsuarioRepository {
    
    @Override
    Usuario findByEmail(String email);
    
    
}
