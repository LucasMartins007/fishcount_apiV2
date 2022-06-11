
package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPagamentoParcelaRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoParcelaRepositoryImpl extends GenericImpl<PagamentoParcela, Integer> implements CustomPagamentoParcelaRepository {

}
