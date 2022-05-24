package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.dao.GenericImpl;

import com.fishcount.api.repository.custom.CustomEspecieRepository;
import com.fishcount.api.repository.spec.EspecieSpec;
import com.fishcount.common.model.entity.Especie;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class EspecieRepositoryImpl extends GenericImpl<Especie, Integer> implements CustomEspecieRepository {

    @Override
    public List<Especie> findAll() {
        return getSpecRepository()
                .findAll(EspecieSpec.orderBy(true, "descricao"));
    }

}
