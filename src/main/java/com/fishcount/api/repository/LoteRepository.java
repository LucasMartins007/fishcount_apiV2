package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomLoteRepository;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lucas
 */
@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer>, JpaSpecificationExecutor<Lote>, CustomLoteRepository {
    
    @Override
    Lote findAtivoByDescricao(String descricao);
    
    @Override
    List<Lote> findAllAtivosByPessoa(Pessoa pessoa);

    @Override
    List<Lote> findAllAtivosByPessoaOrderBy(Pessoa pessoa, String field);
}
