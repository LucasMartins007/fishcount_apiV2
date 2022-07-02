package com.fishcount.api.repository;

import com.fishcount.common.model.entity.ControleEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleEmailRepository extends JpaRepository<ControleEmail, Integer>, JpaSpecificationExecutor<ControleEmail> {
}
