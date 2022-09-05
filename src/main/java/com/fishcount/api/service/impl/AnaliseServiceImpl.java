package com.fishcount.api.service.impl;

import com.fishcount.api.repository.AnaliseRepository;
import com.fishcount.api.repository.ConfiguracaoArracoamentoRepository;
import com.fishcount.api.repository.ParametroTemperaturaRepository;
import com.fishcount.api.service.AnaliseService;
import com.fishcount.api.service.TanqueService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.ConfiguracaoArracoamento;
import com.fishcount.common.model.entity.ParametroTemperatura;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.utils.BigDecimalUtil;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.Utils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AnaliseServiceImpl
        extends AbstractServiceImpl<Analise, Integer, AnaliseDTO>
        implements AnaliseService {

    @Override
    public Analise requisitarInicioAnalise(Integer tanqueId, Integer temperatura) {
        final Tanque tanque = getService(TanqueService.class).findAndValidate(tanqueId);

        final Analise analise = gerarAnalise(tanque, temperatura);

        return getRepository().save(analise);
    }

    @Override
    public Analise simularAnaliseConcluida(Integer tanqueId, Integer analiseId, Integer temperatura) {
        final Tanque tanque = getService(TanqueService.class).findAndValidate(tanqueId);

        return simularAnalise(tanque, analiseId, temperatura);
    }

    @Override
    public List<Analise> listarPorTanque(Integer tanqueId) {
        final Tanque tanque = getService(TanqueService.class).findAndValidate(tanqueId);

        return getRepository(AnaliseRepository.class).findAllByTanque(tanque);
    }

    private Analise simularAnalise(Tanque tanque, Integer analiseId, Integer temperatura) {
        final Analise managedAnalise = findAndValidate(analiseId);
        if (!EnumStatusAnalise.AGUARDANDO_ANALISE.equals(managedAnalise.getStatusAnalise())){
            throw new FcRuntimeException(EnumFcDomainException.ANALISE_NAO_INICIADA, analiseId);
        }
        Analise analise =  prepararAnaliseConcluida(managedAnalise, tanque, temperatura);
        analise.setDataAnalise(managedAnalise.getDataAnalise());
        analise.setTanque(tanque);
        return analise;
    }

    private Analise gerarAnalise(Tanque tanque, Integer temperatura) {
        final List<Analise> analisesConcluidas = getRepository(AnaliseRepository.class).findAllByTanqueAndStatus(tanque, EnumStatusAnalise.ANALISE_CONCLUIDA);
        final Analise managedAnalise = ListUtil.first(analisesConcluidas);
        if (Utils.isEmpty(managedAnalise)) {
            final Analise analise = new Analise();
            analise.setDataAnalise(DateUtil.getDate());
            analise.setTanque(tanque);

            return prepararAnaliseConcluida(analise, tanque, temperatura);
        }
        return prepararAnaliseSonar(managedAnalise);
    }

    private Analise prepararAnaliseSonar(Analise managedAnalise) {
        final Analise analise = new Analise();
        analise.setStatusAnalise(EnumStatusAnalise.AGUARDANDO_ANALISE);
        analise.setDataAnalise(DateUtil.getDate());
        analise.setTanque(managedAnalise.getTanque());

        return analise;
    }

    private Analise prepararAnaliseConcluida(Analise analise, Tanque tanque, Integer temperatura) {
        final BigDecimal pesoVivoMedio = calcularPesoMedioPeixesTotal(tanque.getPesoInicial(), tanque.getQtdePeixe(), tanque.getUnidadePeso());
        analise.setPesoMedioTanque(pesoVivoMedio);

        final ConfiguracaoArracoamento configuracaoArracoamento = getRepository(ConfiguracaoArracoamentoRepository.class).findByPeso(tanque.getPesoInicial());
        analise.setFrequenciaAlimentacaoDiaria(configuracaoArracoamento.getFrequenciaDia());

        final BigDecimal qtdeRacaoDiaria = calcularQtdeRacaoDiaria(tanque, temperatura, pesoVivoMedio, configuracaoArracoamento);
        analise.setQtdeMediaRacaoDiaria(qtdeRacaoDiaria);

        final BigDecimal qtdeRacaoRefeicao = calcularQuantidadeRacaoRefeicao(configuracaoArracoamento.getFrequenciaDia(), qtdeRacaoDiaria);
        analise.setQtdeRacaoRefeicao(qtdeRacaoRefeicao);

        analise.setStatusAnalise(EnumStatusAnalise.ANALISE_CONCLUIDA);
        return analise;
    }

    private BigDecimal calcularQtdeRacaoDiaria(Tanque tanque, Integer temperatura, BigDecimal pesoVivoMedio, ConfiguracaoArracoamento configuracaoArracoamento) {
        final BigDecimal pesoVivoDia = BigDecimalUtil
                .divide(configuracaoArracoamento.getPorcentagemPesoVivoDia(), BigDecimal.valueOf(100), 4);

        final BigDecimal qtdeRacaoDiaria = BigDecimalUtil.truncBig(pesoVivoMedio.multiply(pesoVivoDia), 2);
        if (!tanque.isPossuiMedicaoTemperatura() || temperatura == null) {
            return qtdeRacaoDiaria;
        }
        return aplicarDescontoTemperatura(temperatura, qtdeRacaoDiaria);
    }

    private BigDecimal aplicarDescontoTemperatura(Integer temperatura, BigDecimal qtdeRacaoDiaria) {
        final ParametroTemperatura parametroTemperatura = getRepository(ParametroTemperaturaRepository.class).findByTemperatura(temperatura);
        final BigDecimal porcentagemDescontoRacao = BigDecimalUtil
                .divide(parametroTemperatura.getPorcentagemDescontoRacao(), BigDecimal.valueOf(100), 2);

        return qtdeRacaoDiaria.multiply(porcentagemDescontoRacao);
    }

    private BigDecimal calcularQuantidadeRacaoRefeicao(Integer frequenciaDia, BigDecimal qtdeRacaoDiaria) {
        return BigDecimalUtil.divide(qtdeRacaoDiaria, BigDecimal.valueOf(frequenciaDia), 2);
    }

    private BigDecimal calcularPesoMedioPeixesTotal(BigDecimal pesoPeixe, Integer qtdePeixes, EnumUnidadePeso unidadePeso) {
        if (EnumUnidadePeso.KILO.equals(unidadePeso)) {
            return BigDecimalUtil.truncBig(pesoPeixe.multiply(BigDecimal.valueOf(qtdePeixes)), 2);
        }
        final BigDecimal pesoPeixeQuilo = BigDecimalUtil.divide(pesoPeixe, BigDecimal.valueOf(1000), 2);
        return BigDecimalUtil.truncBig(pesoPeixeQuilo.multiply(BigDecimal.valueOf(qtdePeixes)), 2);
    }
}
