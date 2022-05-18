package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer>{
    
}
