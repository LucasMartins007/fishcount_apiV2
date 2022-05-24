package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomLoteRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.api.repository.spec.LoteSpec;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;

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
