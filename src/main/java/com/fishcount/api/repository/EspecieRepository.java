package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas Martins
 */
public interface EspecieRepository extends JpaRepository<Especie, Integer> {
}
