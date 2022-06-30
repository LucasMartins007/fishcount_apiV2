package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPagamentoRepository;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public interface PagamentoRepository
        extends JpaRepository<Pagamento, Integer>, JpaSpecificationExecutor<Pagamento>, CustomPagamentoRepository {

    @Override
    List<Pagamento> findAllPagamentoByUsuario(Pessoa pessoa);

    @Override
    Pagamento findPagamentoByUsuarioAndId(Pessoa pessoa, Integer id);

    @Override
    Pagamento findPagamentoByPessoaAndStatus(Pessoa pessoa, List<EnumStatusPagamento> status);
}
