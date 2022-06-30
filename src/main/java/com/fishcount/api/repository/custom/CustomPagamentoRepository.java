package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public interface CustomPagamentoRepository {

    List<Pagamento> findAllPagamentoByUsuario(Pessoa pessoa);
    
    Pagamento findPagamentoByUsuarioAndId(Pessoa pessoa, Integer id);

    Pagamento findPagamentoByPessoaAndStatus(Pessoa pessoa, List<EnumStatusPagamento> status);
}
