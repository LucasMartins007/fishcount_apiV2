package com.fishcount.api.service;

import com.fishcount.api.mock.EmailMock;
import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.repository.EmailRepository;
import com.fishcount.api.service.impl.EmailServiceImpl;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.utils.DateUtil;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private EmailValidator emailValidator;

    private Email emailMock;

    private Pessoa pessoaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailMock = EmailMock.criarMock(PessoaMock.criarMock());
        pessoaMock = PessoaMock.criarMock();
    }

    @Test
    @DisplayName("Testar consulta de email, retorno não deve ser nulo.")
    void testarFindByEmail_retornoNaoDeveSerNulo() {
        when(emailRepository.findAtivoByDescricao(emailMock.getDescricao()))
                .thenReturn(emailMock);

        assertNotNull(emailService.findByEmail(emailMock),
                "Email não deve ser nulo");
    }

    @Test
    @DisplayName("Testar consulta de email, retorno deve ser nulo.")
    void testarFindByEmail_retornoDeveSerNulo() {
        when(emailRepository.findAtivoByDescricao(emailMock.getDescricao()))
                .thenReturn(null);

        assertNull(emailService.findByEmail(emailMock),
                "Email deve ser nulo");
    }

    @Test
    @DisplayName("Testar inclusão de email, dados do email preparados para inserção corretamente.")
    void testarIncluirEmail_DadosEmailPreparados() {
        when(emailRepository.save(any(Email.class)))
                .thenReturn(emailMock);

       final Email email = emailService.incluir(pessoaMock, emailMock);
       assertTrue(email.isAtivo());
       assertEquals(email.getDescricao(), emailMock.getDescricao());
       assertSame(pessoaMock, email.getPessoa());
       assertNotNull(email.getDataInclusao());
       assertNotNull(email.getDataAtualizacao());
    }

    @Test
    @DisplayName("Testar inclusão de email, descricao nula deve se manter nula para validação posterior nos validators.")
    void testarValidacaoEmail_CampoDescricaoNulo() {
        emailMock.setDescricao(null);
        when(emailRepository.save(any(Email.class)))
                .thenReturn(emailMock);

        final Email email = emailService.incluir(pessoaMock, emailMock);

        assertNull(email.getDescricao());
    }

    @Test
    @DisplayName("Testar edição de email, dados do email preparados para edição corretamente.")
    void testarEdicao_DadosEmailPreparados() {
        when(emailRepository.findById(1))
                .thenReturn(Optional.of(emailMock));
        emailService.editar(1, emailMock);

        assertTrue(emailMock.isAtivo());
        assertEquals( 1, emailMock.getId());
        assertNotNull(emailMock.getPessoa());
        assertNotNull(emailMock.getDataAtualizacao());
        assertNotNull(emailMock.getDataInclusao());
    }

    @Test
    @DisplayName("Testar edição de email, email com o id informado não encontrado.")
    void testarEdicao_EmailNaoEncontrado() {
        when(emailRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(FcRuntimeException.class,
                () -> emailService.editar(1, emailMock),
                "Não foi possível localizar nenhum registro de Email com o ID 1.");
    }

    @Test
    @DisplayName("Testar edição de email, email editado esta sendo salvo.")
    void testarEdicao_EstaSendoSalvo() {
        when(emailRepository.findById(1))
                .thenReturn(Optional.of(emailMock));

        emailService.editar(1, emailMock);

        verify(emailRepository, times(1)).save(emailMock);
    }

    @Test
    @DisplayName("Testar listagens de email por pessoas, emails do id da pessoa referenciado encontrados.")
    void testarListagem_ListagemNaoVazia() {
        when(emailRepository.findAllByPessoa(1))
                .thenReturn(Collections.singletonList(emailMock));

        List<Email> emails = emailRepository.findAllByPessoa(1);

        assertNotNull(emails);
        assertNotEquals(Collections.emptyList(), emails);
    }

    @Test
    @DisplayName("Testar listagens de email por pessoas, emails do id da pessoa referenciado não encontrados.")
    void testarListagem_ListagemVazia() {
        when(emailRepository.findAllByPessoa(1))
                .thenReturn(Collections.emptyList());

        List<Email> emails = emailRepository.findAllByPessoa(1);

        assertNotNull(emails);
        assertEquals(Collections.emptyList(), emails);
    }

    @Test
    @DisplayName("Testar inativação de email, preparação para inativacao.")
    void testarInativacao_PreparacaoParaInativacao() {
        when(emailRepository.findById(1))
                .thenReturn(Optional.of(emailMock));

        emailService.inativar(1, 1);

        assertFalse(emailMock.isAtivo());
    }

    @Test
    @DisplayName("Testar inativação de email, email com o id informado não encontrado.")
    void testarInativacao_EmailNaoEncontrado() {
        when(emailRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(FcRuntimeException.class,
                () -> emailService.inativar(1, 1),
                "Não foi possível localizar nenhum registro de Email com o ID 1.");
    }

    @Test
    @DisplayName("Testar inativação de email, email inativado esta sendo salvo.")
    void testarInativacao_EstaSendoSalvo() {
        when(emailRepository.findById(1))
                .thenReturn(Optional.of(emailMock));

        emailService.inativar(1, 1);

        verify(emailRepository, times(1)).save(emailMock);
    }

}
