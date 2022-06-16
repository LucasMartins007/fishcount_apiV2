package com.fishcount.api.repository;

import com.fishcount.common.model.entity.log.erros.PixHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixHistoricoRepository extends JpaRepository<PixHistorico, Integer> {
}
