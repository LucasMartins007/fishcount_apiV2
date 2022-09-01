package com.fishcount.api.service.impl;

import com.fishcount.api.repository.AnaliseRepository;
import com.fishcount.api.repository.ConfiguracaoArracoamentoRepository;
import com.fishcount.api.service.AnaliseService;
import com.fishcount.api.service.TanqueService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.ConfiguracaoArracoamento;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.utils.BigDecimalUtil;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.Utils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AnaliseServiceImpl extends AbstractServiceImpl<Analise, Integer, AnaliseDTO> implements AnaliseService {

    @Override
    public Analise requisitarInicioAnalise(Integer tanqueId) {
        final Tanque tanque = getService(TanqueService.class).findAndValidate(tanqueId);

        final Analise analise = onPrepareInsert(tanque);

        return getRepository().save(analise);
    }

    private Analise onPrepareInsert(Tanque tanque) {
        final List<Analise> analisesConcluidas = getRepository(AnaliseRepository.class).findAllByTanqueAndStatus(tanque, EnumStatusAnalise.ANALISE_CONCLUIDA);
        final Analise analise = ListUtil.first(analisesConcluidas);
        if (Utils.isEmpty(analise)) {
           return prepararPrimeiraAnalise(tanque);
        }
        analise.setStatusAnalise(EnumStatusAnalise.AGUARDANDO_ANALISE);

        return analise;
    }

    private Analise prepararPrimeiraAnalise(Tanque tanque) {
        final Analise analise = new Analise();

        final BigDecimal pesoMedio = calcularPesoMedioPeixesTotal(tanque.getPesoInicial(), tanque.getQtdePeixe());
        analise.setPesoMedioTanque(pesoMedio);

        final ConfiguracaoArracoamento configuracaoArracoamento = getRepository(ConfiguracaoArracoamentoRepository.class).findByPeso(tanque.getPesoInicial());
        analise.setFrequenciaAlimentacaoDiaria(configuracaoArracoamento.getFrequenciaDia());

        final BigDecimal qtdeRacaoDiaria = calcularQuantidadeRacaoDiaria(configuracaoArracoamento, pesoMedio);
        analise.setQtdeMediaRacaoDiaria(qtdeRacaoDiaria);

        final BigDecimal qtdeRacaoRefeicao = qtdeRacaoDiaria.multiply(BigDecimal.valueOf(configuracaoArracoamento.getFrequenciaDia()));
        analise.setQtdeRacaoRefeicao(qtdeRacaoRefeicao);

        analise.setDateAnalise(DateUtil.getDate());
        analise.setTanque(tanque);
        analise.setStatusAnalise(EnumStatusAnalise.ANALISE_CONCLUIDA);
        return analise;
    }

    private BigDecimal calcularPesoMedioPeixesTotal(BigDecimal pesoPeixe, Integer qtdePeixes) {
        return BigDecimalUtil.truncBig(pesoPeixe.multiply(BigDecimal.valueOf(qtdePeixes)), 2);
    }

    private BigDecimal calcularQuantidadeRacaoDiaria(ConfiguracaoArracoamento configuracaoArracoamento, BigDecimal pesoMedio) {
        final BigDecimal pesoVivoDia = BigDecimalUtil.divide(configuracaoArracoamento.getPorcentagemPesoVivoDia(), BigDecimal.valueOf(100), 2);
        return pesoMedio.multiply(pesoVivoDia);
    }
}
