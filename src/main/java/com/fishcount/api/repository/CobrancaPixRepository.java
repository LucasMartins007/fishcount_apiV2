
package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomCobrancaPixRepository;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface CobrancaPixRepository extends JpaRepository<CobrancaPix, Integer>, JpaSpecificationExecutor<CobrancaPix>, CustomCobrancaPixRepository {

    @Override
    CobrancaPix findByPagamentoParcela(Integer idParcela);
}
