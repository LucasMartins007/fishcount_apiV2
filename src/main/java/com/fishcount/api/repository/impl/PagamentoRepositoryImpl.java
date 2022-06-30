package com.fishcount.api.repository.impl;

import com.fishcount.api.infrastructure.spec.PagamentoSpec;
import com.fishcount.api.repository.custom.CustomPagamentoRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas Martins
 */
@Repository
public class PagamentoRepositoryImpl extends GenericImpl<Pagamento, Integer> implements CustomPagamentoRepository {

    @Override
    public List<Pagamento> findAllPagamentoByUsuario(Pessoa pessoa) {
        return getSpecRepository()
                .findAll(
                        PagamentoSpec.byPessoa(pessoa)
                );
    }

    @Override
    public Pagamento findPagamentoByUsuarioAndId(Pessoa pessoa, Integer id) {
        return getSpecRepository()
                .findOne(
                        PagamentoSpec.byId(id)
                                .and(PagamentoSpec.byPessoa(pessoa))
                ).orElse(null);
    }

    @Override
    public Pagamento findPagamentoByPessoaAndStatus(Pessoa pessoa, List<EnumStatusPagamento> status) {
        return getSpecRepository()
                .findOne(
                        PagamentoSpec.byPessoa(pessoa)
                                .and(PagamentoSpec.byStatus(status)))
                .orElse(null);
    }

}
