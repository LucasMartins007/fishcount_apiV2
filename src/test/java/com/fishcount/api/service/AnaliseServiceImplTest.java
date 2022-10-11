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
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

}
