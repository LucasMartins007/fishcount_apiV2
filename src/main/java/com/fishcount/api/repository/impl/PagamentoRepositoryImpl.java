package com.fishcount.api.repository.impl;

import com.fishcount.api.infrastructure.spec.PagamentoSpec;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Pagamento;
import org.springframework.stereotype.Repository;
import com.fishcount.api.repository.custom.CustomPagamentoRepository;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class PagamentoRepositoryImpl extends GenericImpl<Pagamento, Integer> implements CustomPagamentoRepository {

    @Override
    public List<Pagamento> findAllPagamentoByUsuario(Usuario usuario) {
        return getSpecRepository()
                .findAll(
                        PagamentoSpec.pagamentosByUsuario(usuario)
                );
    }

    @Override
    public Pagamento findPagamentoByUsuarioAndId(Usuario usuario, Integer id) {
        return getSpecRepository()
                .findOne(
                        PagamentoSpec.pagamentoById(id)
                                .and(PagamentoSpec.pagamentosByUsuario(usuario))
                ).orElse(null);
    }

}
