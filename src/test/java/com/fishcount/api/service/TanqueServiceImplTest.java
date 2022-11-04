package com.fishcount.api.service;

import com.fishcount.api.mock.EspecieMock;
import com.fishcount.api.mock.LoteMock;
import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.mock.TanqueMock;
import com.fishcount.api.repository.TanqueRepository;
import com.fishcount.api.service.impl.TanqueServiceImpl;
import com.fishcount.api.validators.EspecieValidator;
import com.fishcount.api.validators.TanqueValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TanqueServiceImplTest {

    @InjectMocks
    private TanqueServiceImpl tanqueService;

    @Mock
    private TanqueRepository tanqueRepository;

    @Mock
    private TanqueValidator tanqueValidator;

    @Mock
    private EspecieValidator especieValidator;

    @Mock
    private LoteService loteService;

    @Mock
    private EspecieService especieService;

    private Tanque tanqueMock;

    private Lote loteMock;

    private Especie especieMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tanqueMock = TanqueMock.criarMock();
        loteMock = LoteMock.criarMock(PessoaMock.criarMock());
        especieMock = EspecieMock.criarMock();
    }

    @Test
    @DisplayName("Testar inclusão, testando a preparação correta dos dados do tanque na inserção.")
    void testarInclusao_PreparacaoDosDados() {
        Integer qtdePeixes = 5000;

        tanqueMock.setAtivo(false);
        tanqueMock.setQtdePeixe(qtdePeixes);

        when(loteService.findAndValidate(1))
                .thenReturn(loteMock);

        when(especieService.findAndValidate(1))
                .thenReturn(especieMock);

        when(tanqueRepository.save(tanqueMock))
                .thenReturn(tanqueMock);

        final Tanque tanque = tanqueService.incluir(1, tanqueMock);
        assertEquals(EnumStatusAnalise.ANALISE_NAO_REALIZADA, tanque.getStatusAnalise());
        assertEquals(qtdePeixes, tanque.getQtdUltimaAnalise());
        assertNotNull(tanque.getDataProximaAnalise());
        assertNotNull(tanque.getDataUltimaAnalise());
        assertNotNull(tanque.getDataUltimoTratamento());

        assertTrue(tanque.isAtivo());
        assertEquals(tanque.getLote(), tanqueMock.getLote());
        assertEquals(tanque.getEspecie(), especieMock);

        verify(especieService, times(1))
                .findAndValidate(tanqueMock.getEspecie().getId());

        verify(tanqueRepository, times(1))
                .save(tanqueMock);
    }

    @Test
    @DisplayName("Testar inclusão, testando a preparação correta dos" +
            " dados do tanque na inserção sem a especificação da espécie.")
    void testarInclusao_InclusaoSemEspecie() {
        Integer qtdePeixes = 5000;

        tanqueMock.setEspecie(null);
        tanqueMock.setAtivo(false);
        tanqueMock.setQtdePeixe(qtdePeixes);

        when(loteService.findAndValidate(1))
                .thenReturn(loteMock);

        when(tanqueRepository.save(tanqueMock))
                .thenReturn(tanqueMock);

        final Tanque tanque = tanqueService.incluir(1, tanqueMock);
        assertNull(tanque.getEspecie());

        assertEquals(EnumStatusAnalise.ANALISE_NAO_REALIZADA, tanque.getStatusAnalise());
        assertEquals(qtdePeixes, tanque.getQtdUltimaAnalise());
        assertNotNull(tanque.getDataProximaAnalise());
        assertNotNull(tanque.getDataUltimaAnalise());
        assertNotNull(tanque.getDataUltimoTratamento());

        assertTrue(tanque.isAtivo());
        assertEquals(tanque.getLote(), tanqueMock.getLote());


        verify(especieService, times(0))
                .findAndValidate(1);

        verify(tanqueRepository, times(1))
                .save(tanqueMock);
    }

    @Test
    @DisplayName("Testar edição, testando se preparação de dados na edição esta ocorrendo de forma correta.")
    void testarEdicao_PreparacaoDeDadosNaEdicao() {
        tanqueMock.setAtivo(false);

        when(loteService.findAndValidate(1))
                .thenReturn(loteMock);

        when(tanqueRepository.findById(1))
                .thenReturn(Optional.of(tanqueMock));

        tanqueService.editar(1, 1, 1, tanqueMock);
        assertTrue(tanqueMock.isAtivo(), "Deve manter o tanque ativo.");

        assertEquals(EnumStatusAnalise.FALHA_ANALISE, tanqueMock.getStatusAnalise(),
                "Deve manter o mesmo status da análise cadastrado no banco.");
        assertEquals(tanqueMock.getLote(), loteMock,
                "Deve manter o mesmo lote de referencia na edição.");
        assertNotNull(tanqueMock.getAnalises(),
                "Deve manter as análises já realizadas na edição.");
        assertNotNull(tanqueMock.getId(),
                "Id não deve ser nulo para realizar a edição.");
        assertNotNull(tanqueMock.getQtdUltimaAnalise(),
                "Quantidade na ultima análise não deve ser nula.");
        assertNotNull(tanqueMock.getLote(),
                "Referencia ao lote não deve ser nula na edição.");

    }

    @Test
    @DisplayName("Testar listagem, testar listagem quando for ordenada.")
    void testarListagem_TestarListagemOrdenada() {
        when(tanqueRepository.findAllByPessoaAndLoteOrderBy(1, 1, "descricao"))
                .thenReturn(Collections.singletonList(tanqueMock));

        final List<Tanque> tanques = tanqueService.listarFromPessoaAndLote(1, 1, "descricao");
        assertNotNull(tanques, "Lista ordenada não deve ser nula.");
        assertEquals(Collections.singletonList(tanqueMock), tanques, "Lista ordenada não deve ser vazia.");

        verify(tanqueRepository, times(0))
                .findAllByPessoaAndLote(1, 1);

        verify(tanqueRepository, times(1))
                .findAllByPessoaAndLoteOrderBy(1, 1, "descricao");
    }

    @Test
    @DisplayName("Testar listagem, testar listagem sem necessidade de ordenação.")
    void testarListagem_TestarListagemNoaOrdenada() {
        when(tanqueRepository.findAllByPessoaAndLote(1, 1))
                .thenReturn(Collections.singletonList(tanqueMock));

        final List<Tanque> tanques = tanqueService.listarFromPessoaAndLote(1, 1, null);
        assertNotNull(tanques, "Lista ordenada não deve ser nula.");
        assertEquals(Collections.singletonList(tanqueMock), tanques, "Lista ordenada não deve ser vazia.");

        verify(tanqueRepository, times(1))
                .findAllByPessoaAndLote(1, 1);

        verify(tanqueRepository, times(0))
                .findAllByPessoaAndLoteOrderBy(1, 1, "descricao");
    }

    @Test
    @DisplayName("Testar inativação, testar se esta sendo setado falso no campo ativo do tanque.")
    void testarInativacao_SetadoFalsoCampoAtivo() {
        tanqueMock.setAtivo(true);
        when(tanqueRepository.findByPessoaAndLoteAndId(1, 1, 1))
                .thenReturn(tanqueMock);

        tanqueService.inativarTanque(1, 1, 1);
        assertFalse(tanqueMock.isAtivo());
    }

    @Test
    @DisplayName("Testar inativação, testar se esta sendo setado falso no campo ativo do tanque.")
    void testarInativacao_TanqueNaoEncontrado() {
        when(tanqueRepository.findByPessoaAndLoteAndId(1, 1, 1))
                .thenReturn(null);

        final FcRuntimeException exception = assertThrows(FcRuntimeException.class,
                () -> tanqueService.inativarTanque(1, 1, 1),
                "Esperava tanqueService.inativarTanque lançar exceção, mas não lançou.");

        assertEquals("O tanque 1 não existe ou não é do usuário logado, por favor, tente novamente!",
                exception.getMessage());
    }

    @Test
    @DisplayName("Testar busca por id, testar retorno da busca de tanque por id.")
    void testarBuscaPorId_BuscaPorID() {
        when(tanqueRepository.findByPessoaAndLoteAndId(1, 1, 1))
                .thenReturn(tanqueMock);

        final Tanque tanque = tanqueService.encontrarPorId(1, 1, 1);
        assertNotNull(tanque);
    }


}
