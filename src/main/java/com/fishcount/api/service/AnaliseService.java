package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;

import java.math.BigDecimal;
import java.util.List;

public interface AnaliseService extends IAbstractService<Analise, Integer, AnaliseDTO> {

    Analise requisitarInicioAnalise(Integer tanqueId, BigDecimal pesoAtual, EnumUnidadePeso unidadePeso, BigDecimal temperatura);

    Analise simularAnaliseConcluida(Integer tanqueId, Integer analiseId, Integer qtdePeixes, BigDecimal temperatura);

    List<Analise> listarPorTanque(Integer tanqueId, EnumStatusAnalise statusAnalise);
}
