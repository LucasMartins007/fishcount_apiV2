package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
    
}
