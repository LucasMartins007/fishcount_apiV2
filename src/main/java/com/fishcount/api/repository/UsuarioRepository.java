package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomUsuarioRepository;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario>, CustomUsuarioRepository {
    
    @Override
    Usuario findByEmailPrincipal(String email);
    
    
}
