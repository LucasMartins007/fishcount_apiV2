package com.fishcount.api.service;

import com.fishcount.api.mock.LoteMock;
import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.repository.LoteRepository;
import com.fishcount.api.service.impl.LoteServiceImpl;
import com.fishcount.api.validators.LoteValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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
        loteMock = LoteMock.criarMock();
        pessoaMock = PessoaMock.criarMock();
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
        pessoaMock.setId(5);

        assertThrows(FcRuntimeException.class,
                () -> loteService.inativar(pessoaMock.getId(), loteMock),
                "Lote 1 não pertence a pessoa 5");
    }

}
