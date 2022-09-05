package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;

import java.util.List;

public interface CustomAnaliseRepository {

    List<Analise> findAllByTanqueAndStatus(Tanque tanque, EnumStatusAnalise statusAnalise);

    Analise findByIdAndStatus(Integer analiseId, EnumStatusAnalise statusAnalise);

    List<Analise> findAllByTanque(Tanque tanque);
}
