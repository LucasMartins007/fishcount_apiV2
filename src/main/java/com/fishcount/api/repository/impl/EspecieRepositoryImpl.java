package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomEspecieRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.EspecieSpec;
import com.fishcount.common.model.entity.Especie;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class EspecieRepositoryImpl extends RepositoryImpl<Especie, Integer> implements CustomEspecieRepository {

    @Override
    public List<Especie> findAllOrderByDescricao() {
        return getSpecRepository()
                .findAll(EspecieSpec.orderBy(true, "descricao"));
    }

    @Override
    public Especie findByDescricao(String descricao) {
        return getSpecRepository()
                .findOne(EspecieSpec.findByDescricao(descricao))
                .orElse(null);
    }
}
