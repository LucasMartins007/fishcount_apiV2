package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Tanque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lucas
 */
@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Integer> {

    
    List<Tanque> findAllByLote(Lote lote);
}
