package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.infrastructure.spec.LoteSpec;
import com.fishcount.api.repository.custom.CustomLoteRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class LoteRepositoryImpl extends GenericImpl<Lote, Integer> implements CustomLoteRepository {

    @Override
    public Lote findByDescricao(String descricao) {
        return getSpecRepository()
                .findOne(
                        LoteSpec.loteByDescricao(descricao))
                .orElse(null);
    }

    @Override
    public List<Lote> findAllByUsuario(Usuario usuario) {
        return getSpecRepository()
                .findAll(
                        LoteSpec.loteByUsuario(usuario)
                                .and(LoteSpec.orderBy(true, "descricao")));
    }

}
