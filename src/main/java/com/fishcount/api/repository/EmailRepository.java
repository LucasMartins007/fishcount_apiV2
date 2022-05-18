package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author lucas
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

    Optional<Email> findByDescricaoAndAtivoTrue(String descricao);
}
