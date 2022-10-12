package com.fishcount.api.service.impl;

import com.fishcount.api.repository.AnaliseRepository;
import com.fishcount.api.repository.ConfiguracaoArracoamentoRepository;
import com.fishcount.api.repository.ParametroTemperaturaRepository;
import com.fishcount.api.repository.TanqueRepository;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnaliseServiceImpl
        extends AbstractServiceImpl<Analise, Integer, AnaliseDTO>
        implements AnaliseService {

    private final TanqueService tanqueService;

    private final TanqueRepository tanqueRepository;

    private final AnaliseRepository analiseRepository;

    private final ParametroTemperaturaRepository parametroTemperaturaRepository;

    private final ConfiguracaoArracoamentoRepository configuracaoArracoamentoRepository;

    @Override
    public Analise requisitarInicioAnalise(Integer tanqueId, BigDecimal pesoAtual, EnumUnidadePeso unidadePeso, BigDecimal temperatura) {
        final Tanque tanque = tanqueService.findAndValidate(tanqueId);

        final Analise analise = gerarAnalise(tanque, temperatura);

        atualizarDadosTanque(pesoAtual, unidadePeso, tanque, analise);

        return analiseRepository.save(analise);
    }

    @Override
    public Analise simularAnaliseConcluida(Integer tanqueId, Integer analiseId, Integer qtdePeixes, BigDecimal temperatura) {
        final Tanque tanque = tanqueService.findAndValidate(tanqueId);

        final Analise analise = simularAnalise(tanque, analiseId, qtdePeixes, temperatura);

        atualizarDadosTanque(null, null, tanque, analise);

        return analiseRepository.save(analise);
    }

    private void atualizarDadosTanque(BigDecimal pesoUnitario, EnumUnidadePeso unidadePeso, Tanque tanque, Analise analise) {
        tanque.setDataUltimaAnalise(DateUtil.getDate());
        tanque.setDataAtualizacao(DateUtil.getDate());
        tanque.setStatusAnalise(analise.getStatusAnalise());
        tanque.setPesoUnitario(pesoUnitario);
        tanque.setUnidadePeso(unidadePeso);
        tanque.setQtdUltimaAnalise(analise.getQtdePeixe());

        tanqueRepository.save(tanque);
    }

    @Override
    public List<Analise> listarPorTanque(Integer tanqueId, EnumStatusAnalise statusAnalise) {
        final Tanque tanque = tanqueService.findAndValidate(tanqueId);

        if (Utils.isEmpty(statusAnalise)) {
            return analiseRepository.findAllByTanque(tanque);
        }
        return analiseRepository.findAllByTanqueAndStatus(tanque, statusAnalise);
    }

    private Analise simularAnalise(Tanque tanque, Integer analiseId, Integer qtdePeixes, BigDecimal temperatura) {
        final Analise managedAnalise = analiseRepository.findByIdAndStatus(analiseId, EnumStatusAnalise.AGUARDANDO_ANALISE);
        if (Utils.isEmpty(managedAnalise)) {
            throw new FcRuntimeException(EnumFcDomainException.ANALISE_NAO_INICIADA, analiseId);
        }
        final Analise analise = prepararAnaliseConcluida(managedAnalise, tanque, temperatura);
        analise.setDataAnalise(managedAnalise.getDataAnalise());
        analise.setTanque(tanque);
        analise.setQtdePeixe(tanque.getQtdePeixe());

        return analise;
    }

    /**
     * Verifica se o tanque possui análises concluidas
     * <p>
     * caso sim, quer dizer que esse tanque já finalizou alguma análise em seu ciclo de vida, então é gerado uma
     * nova análise com status AGUARDANDO_ANALISE.
     * <p>
     * caso não, quer dizer que é a primeira ou análise do tanque, então é gerado uma nova análise com o
     * status ANALISE_CONCLUIDA, fazendo o cálculo com os dados pré-cadastrados inicialmente.
     */
    private Analise gerarAnalise(Tanque tanque, BigDecimal temperatura) {
        final List<Analise> analisesConcluidas = analiseRepository.findAllByTanqueAndStatus(tanque, EnumStatusAnalise.ANALISE_CONCLUIDA);
        final Analise managedAnalise = ListUtil.first(analisesConcluidas);
        if (Utils.isEmpty(managedAnalise)) {
            final Analise analise = new Analise();
            analise.setDataAnalise(DateUtil.getDate());
            analise.setTanque(tanque);
            analise.setTemperaturaAgua(temperatura);

            return prepararAnaliseConcluida(analise, tanque, temperatura);
        }
        return prepararAnaliseSonar(tanque, managedAnalise);
    }

    private Analise prepararAnaliseSonar(Tanque tanque, Analise managedAnalise) {
        final List<Analise> analisesAguardando = analiseRepository.findAllByTanqueAndStatus(tanque, EnumStatusAnalise.AGUARDANDO_ANALISE);
        if (ListUtil.isNotNullOrNotEmpty(analisesAguardando)) {
            throw new FcRuntimeException(EnumFcDomainException.ANALISE_AGUARDANDO_JA_EXISTE, tanque.getDescricao());
        }
        final Analise analise = new Analise();
        analise.setStatusAnalise(EnumStatusAnalise.AGUARDANDO_ANALISE);
        analise.setDataAnalise(DateUtil.getDate());
        analise.setTanque(managedAnalise.getTanque());
        analise.setTemperaturaAgua(managedAnalise.getTemperaturaAgua());

        return analise;
    }

    private Analise prepararAnaliseConcluida(Analise analise, Tanque tanque, BigDecimal temperatura) {
        final BigDecimal pesoVivoMedio = calcularPesoMedioPeixesTotal(tanque.getPesoUnitario(), tanque.getQtdePeixe(), tanque.getUnidadePeso());
        analise.setPesoMedioTanque(pesoVivoMedio);

        final ConfiguracaoArracoamento configuracaoArracoamento = configuracaoArracoamentoRepository.findByPeso(tanque.getPesoUnitario());
        analise.setFrequenciaAlimentacaoDiaria(configuracaoArracoamento.getFrequenciaDia());
        analise.setTipoRacao(configuracaoArracoamento.getParametroTipoRacao().getDescricao());

        final BigDecimal qtdeRacaoDiaria = calcularQtdeRacaoDiaria(tanque, temperatura, pesoVivoMedio, configuracaoArracoamento);
        analise.setTemperaturaAgua(temperatura);
        analise.setQtdeRacaoDiaria(qtdeRacaoDiaria);
        analise.setUnidadePesoRacaoDiaria(EnumUnidadePeso.KILO);

        final BigDecimal qtdeRacaoRefeicao = calcularQuantidadeRacaoRefeicao(configuracaoArracoamento.getFrequenciaDia(), qtdeRacaoDiaria);
        analise.setQtdeRacaoRefeicao(qtdeRacaoRefeicao);
        analise.setUnidadePesoRacaoRefeicao(EnumUnidadePeso.KILO);

        analise.setStatusAnalise(EnumStatusAnalise.ANALISE_CONCLUIDA);
        return analise;
    }

    private BigDecimal calcularQtdeRacaoDiaria(Tanque tanque, BigDecimal temperatura, BigDecimal pesoVivoMedio, ConfiguracaoArracoamento configuracaoArracoamento) {
        final BigDecimal pesoVivoDia = BigDecimalUtil
                .divide(configuracaoArracoamento.getPorcentagemPesoVivoDia(), BigDecimal.valueOf(100), 4);

        final BigDecimal qtdeRacaoDiaria = BigDecimalUtil.truncBig(pesoVivoMedio.multiply(pesoVivoDia), 2);
        if (!tanque.isPossuiMedicaoTemperatura() || temperatura == null) {
            return qtdeRacaoDiaria;
        }
        return aplicarDescontoTemperatura(temperatura, qtdeRacaoDiaria);
    }

    private BigDecimal aplicarDescontoTemperatura(BigDecimal temperatura, BigDecimal qtdeRacaoDiaria) {
        final ParametroTemperatura parametroTemperatura = parametroTemperaturaRepository.findByTemperatura(temperatura);
        if (Utils.isEmpty(parametroTemperatura)) {
            throw new FcRuntimeException(EnumFcDomainException.CONFIGURACAO_TEMPERATURA_NAO_VALIDADA, temperatura);
        }
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
