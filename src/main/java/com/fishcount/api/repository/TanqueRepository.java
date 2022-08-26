package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomCobrancaPixRepository;
import com.fishcount.api.repository.custom.CustomTanqueRepository;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lucas
 */
@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Integer>, JpaSpecificationExecutor<Tanque>, CustomTanqueRepository {
    
    List<Tanque> findAllByLote(Lote lote);

    @Override
    List<Tanque> findAllByPessoaAndLote(Integer pessoaId, Integer loteId);

    @Override
    Tanque findByPessoaAndLoteAndId(Integer pessoaId, Integer loteId, Integer tanqueId);

    @Override
    List<Tanque> findAllByPessoaAndLoteOrderBy(Integer pessoaId, Integer loteId, String orderBy);
}
