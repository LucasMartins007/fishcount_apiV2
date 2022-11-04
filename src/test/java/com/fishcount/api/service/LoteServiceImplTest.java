package com.fishcount.api.service;

import com.fishcount.api.mock.LoteMock;
import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.mock.TanqueMock;
import com.fishcount.api.repository.LoteRepository;
import com.fishcount.api.service.impl.LoteServiceImpl;
import com.fishcount.api.validators.LoteValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Tanque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoteServiceImplTest {

    @InjectMocks
    private LoteServiceImpl loteService;

    @Mock
    private LoteValidator loteValidator;

    @Mock
    private LoteRepository loteRepository;

    private Lote loteMock;

    private Pessoa pessoaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaMock = PessoaMock.criarMock();
        loteMock = LoteMock.criarMock(pessoaMock);
    }

    @Test
    @DisplayName("Testar inclusão, preparação dos dados do lote para inclusão estão correta.")
    void testarInclusao_PrepararDadosInsercao() {
        loteMock.setId(null);
        loteMock.setAtivo(false);
        loteMock.setDescricao(loteMock.getDescricao().toUpperCase());
        when(loteRepository.save(loteMock))
                .thenReturn(loteMock);

        final Lote lote = loteService.incluir(pessoaMock, loteMock);
        assertTrue(lote.isAtivo());
        assertNotNull(pessoaMock);
        assertEquals(pessoaMock, lote.getPessoa());
        assertEquals(loteMock.getDescricao().toLowerCase(), lote.getDescricao().toLowerCase());
    }

    @Test
    @DisplayName("Testar inclusão, preparação dos dados do lote para inclusão estão correta.")
    void testarInclusao_PrepararDadosAtualizacao() {
        loteMock.setAtivo(false);
        when(loteRepository.findById(loteMock.getId()))
                .thenReturn(Optional.of(loteMock));

        loteService.incluir(pessoaMock, loteMock);
        assertTrue(loteMock.isAtivo());
        assertNotNull(pessoaMock);
        assertEquals(pessoaMock, loteMock.getPessoa());
        assertEquals(loteMock.getDescricao().toLowerCase(), loteMock.getDescricao().toLowerCase());

        assertNotNull(loteMock.getTanques());
        assertNotNull(loteMock.getDataAtualizacao());
    }

    @Test
    @DisplayName("Testar edicao, preparação dos dados do lote para edição estão correta.")
    void testarEdicao_PrepararDadosAtualizacao() {
        loteMock.setAtivo(false);
        when(loteRepository.findById(loteMock.getId()))
                .thenReturn(Optional.of(loteMock));

        loteService.editar(pessoaMock, loteMock.getId(), loteMock);
        assertTrue(loteMock.isAtivo());
        assertNotNull(pessoaMock);
        assertEquals(pessoaMock, loteMock.getPessoa());
        assertEquals(loteMock.getDescricao().toLowerCase(), loteMock.getDescricao().toLowerCase());

        assertNotNull(loteMock.getTanques());
        assertNotNull(loteMock.getDataAtualizacao());

    }

    @Test
    @DisplayName("Testar inativação, campo ativo esta sendo setado como false na inativação.")
    void testarInativacao_AtivoSendoSetadoComoFalse() {
        loteService.inativar(pessoaMock.getId(), loteMock);

        assertFalse(loteMock.isAtivo());
        assertNotNull(loteMock.getDataAtualizacao());

        verify(loteRepository, times(1))
                .save(loteMock);
    }

    @Test
    @DisplayName("Testar inativação, pessoa referenciada deve ser a referenciada no lote encontrado.")
    void testarInativacao_InativacaoLoteNaoPertencenteAPessoa() {
        pessoaMock.setId(1);

        assertThrows(FcRuntimeException.class,
                () -> loteService.inativar(5, loteMock),
                "Lote 1 não pertence a pessoa 5");
    }

    @Test
    @DisplayName("Testar listagem, deve retornar uma lista vazia")
    void testarListagem_DeveRetornarVazio() {
        loteMock.getTanques().forEach(tanque -> tanque.setAtivo(false));

        when(loteRepository.findAllAtivosByPessoa(pessoaMock.getId()))
                .thenReturn(Collections.emptyList());

        List<Lote> lotes = loteService.listarFromPessoa(loteMock.getId(), null);
        assertEquals(Collections.emptyList(), lotes);
    }


    @Test
    @DisplayName("Testar listagem, deve retornar uma lista de lotes, sem ordenação.")
    void testarListagem_DeveRetornarListaDeLotes() {
        loteMock.getTanques().forEach(tanque -> tanque.setAtivo(false));

        when(loteRepository.findAllAtivosByPessoa(1))
                .thenReturn(Collections.singletonList(loteMock));

        List<Lote> lotes = loteService.listarFromPessoa(loteMock.getId(), null);
        assertEquals(Collections.singletonList(loteMock), lotes);

        lotes.forEach(lote -> assertEquals(0, lote.getTanques().size()));
    }

    @Test
    @DisplayName("Testar listagem, deve retornar uma lista ordenada de lotes.")
    void testarListagem_RetornarListaOrdenadaDeLotes() {
        loteMock.setTanques(new ArrayList<>());
        loteMock.getTanques().add(TanqueMock.criarMock());
        loteMock.getTanques().add(TanqueMock.criarMock());

        loteMock.getTanques().forEach(tanque -> tanque.setAtivo(false));

        loteMock.getTanques().add(TanqueMock.criarMock());

        when(loteRepository.findAllAtivosByPessoaOrderBy(1, "descricao"))
                .thenReturn(Collections.singletonList(loteMock));

        final List<Lote> lotes = loteService.listarFromPessoa(loteMock.getId(), "descricao");

        lotes.forEach(lote -> assertEquals(1, lote.getTanques().size()));
    }

    @Test
    @DisplayName("Testar validação, Preparação de dados do lote na validação")
    void testarValidacao_PreparacaoDosDadosDoLote() {
        loteService.validarLotes(pessoaMock);

        final List<Lote> lotes = pessoaMock.getLotes();
        lotes.forEach(lote -> {
            assertTrue(lote.isAtivo());
            assertNotNull(pessoaMock);
            assertEquals(pessoaMock, lote.getPessoa());
        });
    }

}
