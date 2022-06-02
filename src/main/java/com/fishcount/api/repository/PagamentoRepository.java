package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPagamentoServiceRepository;
import com.fishcount.common.model.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>, JpaSpecificationExecutor<Pagamento>, CustomPagamentoServiceRepository {

}
