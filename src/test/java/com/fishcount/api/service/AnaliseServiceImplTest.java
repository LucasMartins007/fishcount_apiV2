package com.fishcount.api.service;

import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.repository.AnaliseRepository;
import com.fishcount.api.repository.ConfiguracaoArracoamentoRepository;
import com.fishcount.api.repository.ParametroTemperaturaRepository;
import com.fishcount.api.repository.TanqueRepository;
import com.fishcount.api.service.impl.AnaliseServiceImpl;
import com.fishcount.common.model.entity.Analise;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;

class AnaliseServiceImplTest {

    @InjectMocks
    private AnaliseServiceImpl analiseService;

    @Mock
    private TanqueService tanqueService;

    @Mock
    private TanqueRepository tanqueRepository;

    @Mock
    private AnaliseRepository analiseRepository;

    @Mock
    private ParametroTemperaturaRepository parametroTemperaturaRepository;

    @Mock
    private ConfiguracaoArracoamentoRepository configuracaoArracoamentoRepository;

    private Pessoa pessoaMock;

    private Analise analiseMock;

    private Tanque tanqueMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaMock = PessoaMock.criarMock();
        analiseMock = new Analise();
        tanqueMock = new Tanque();
    }

//    @Test
//    void testarAnalises_AnaliseEstasendoSalva() {
//        tanqueMock.setQtdePeixe(1);
//
//        when(tanqueService.findAndValidate(1))
//                .thenReturn(tanqueMock);
//        when(analiseRepository.findByIdAndStatus(1, EnumStatusAnalise.ANALISE_NAO_REALIZADA))
//                .thenReturn(analiseMock);
//        when(analiseRepository.findAllByTanqueAndStatus(tanqueMock, EnumStatusAnalise.ANALISE_CONCLUIDA))
//                .thenReturn(Collections.singletonList(analiseMock));
//
//        when(analiseRepository.findAllByTanqueAndStatus(tanqueMock, EnumStatusAnalise.ANALISE_NAO_REALIZADA))
//                .thenReturn(Collections.singletonList(analiseMock));
//
//        analiseService.requisitarInicioAnalise(1, BigDecimal.ZERO, EnumUnidadePeso.GRAMA, BigDecimal.ONE);
//
//        verify(analiseRepository, times(1))
//                .save(new Analise());
//    }
}
