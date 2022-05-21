package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomLoteRepository;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author lucas
 */
@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer>, JpaSpecificationExecutor<Lote>, CustomLoteRepository {
    
    @Override
    Lote findByDescricao(String descricao);
    
    @Override
    List<Lote> findAllByUsuario(Usuario usuario);
}
