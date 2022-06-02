package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPagamentoServiceRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Pagamento;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class PagamentoRepositoryImpl extends GenericImpl<Pagamento, Integer> implements CustomPagamentoServiceRepository {

}
