package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;

import java.util.List;

public interface CustomAnaliseRepository {

    List<Analise> findAllByTanqueAndStatus(Tanque tanque, EnumStatusAnalise statusAnalise);

}
