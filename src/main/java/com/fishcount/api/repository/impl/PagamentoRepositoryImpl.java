package com.fishcount.api.repository.impl;

import com.fishcount.api.infrastructure.spec.PagamentoSpec;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import org.springframework.stereotype.Repository;
import com.fishcount.api.repository.custom.CustomPagamentoRepository;
import com.fishcount.common.model.entity.Usuario;

import java.util.List;

/**
 * @author Lucas Martins
 */
@Repository
public class PagamentoRepositoryImpl extends GenericImpl<Pagamento, Integer> implements CustomPagamentoRepository {

    @Override
    public List<Pagamento> findAllPagamentoByUsuario(Usuario usuario) {
        return getSpecRepository()
                .findAll(
                        PagamentoSpec.byUsuario(usuario)
                );
    }

    @Override
    public Pagamento findPagamentoByUsuarioAndId(Usuario usuario, Integer id) {
        return getSpecRepository()
                .findOne(
                        PagamentoSpec.byId(id)
                                .and(PagamentoSpec.byUsuario(usuario))
                ).orElse(null);
    }

    @Override
    public Pagamento findPagamentoByUsuarioAndStatus(Usuario usuario, List<EnumStatusPagamento> status) {
        return getSpecRepository()
                .findOne(
                        PagamentoSpec.byUsuario(usuario)
                                .and(PagamentoSpec.byStatus(status)))
                .orElse(null);
    }

}
