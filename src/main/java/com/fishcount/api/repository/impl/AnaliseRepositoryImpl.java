package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomAnaliseRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.AnaliseSpec;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;

import java.util.List;

public class AnaliseRepositoryImpl
        extends RepositoryImpl<Analise, Integer>
        implements CustomAnaliseRepository {


    @Override
    public List<Analise> findAllByTanqueAndStatus(Tanque tanque, EnumStatusAnalise statusAnalise) {
        return getSpecRepository()
                .findAll(AnaliseSpec.byStatus(statusAnalise)
                        .and(AnaliseSpec.byTanque(tanque))
                        .and(AnaliseSpec.orderBY(true, "dataAnalise")));
    }

    @Override
    public Analise findByIdAndStatus(Integer analiseId, EnumStatusAnalise statusAnalise) {
        return getSpecRepository()
                .findOne(AnaliseSpec.byId(analiseId)
                        .and(AnaliseSpec.byStatus(statusAnalise)))
                .orElse(null);
    }

    @Override
    public List<Analise> findAllByTanque(Tanque tanque) {
        return getSpecRepository()
                .findAll(AnaliseSpec.byTanque(tanque));
    }
}
