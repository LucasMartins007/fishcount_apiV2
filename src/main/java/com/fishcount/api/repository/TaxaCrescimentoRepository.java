package com.fishcount.api.repository;

import com.fishcount.common.model.entity.TaxaCrescimento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas Martins
 */
public interface TaxaCrescimentoRepository extends JpaRepository<TaxaCrescimento, Integer> {
}
