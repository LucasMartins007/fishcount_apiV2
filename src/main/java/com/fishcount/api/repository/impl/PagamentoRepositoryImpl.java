package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Pagamento;
import org.springframework.stereotype.Repository;
import com.fishcount.api.repository.custom.CustomPagamentoRepository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class PagamentoRepositoryImpl extends GenericImpl<Pagamento, Integer> implements CustomPagamentoRepository {

}
