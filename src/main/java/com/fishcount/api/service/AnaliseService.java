package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.enums.EnumStatusAnalise;

import java.util.List;

public interface AnaliseService extends IAbstractService<Analise, Integer, AnaliseDTO> {

    Analise requisitarInicioAnalise(Integer tanqueId, Integer temperatura);

    Analise simularAnaliseConcluida(Integer tanqueId, Integer analiseId, Integer temperatura);

    List<Analise> listarPorTanque(Integer tanqueId, EnumStatusAnalise statusAnalise);
}
