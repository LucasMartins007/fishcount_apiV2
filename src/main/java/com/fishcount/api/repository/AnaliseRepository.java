package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface AnaliseRepository  extends JpaRepository<Analise, Integer>{
    
}
