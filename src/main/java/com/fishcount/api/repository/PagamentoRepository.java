package com.fishcount.api.repository;

import com.fishcount.common.model.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.fishcount.api.repository.custom.CustomPagamentoRepository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>, JpaSpecificationExecutor<Pagamento>, CustomPagamentoRepository {

}
