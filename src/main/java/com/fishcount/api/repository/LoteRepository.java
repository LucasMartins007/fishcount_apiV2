package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author lucas
 */
@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
    
    Optional<Lote> findByDescricao(String descricao);
    
    List<Lote> findByUsuario(Usuario usuario);
}
