package com.fishcount.api.service;

import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.repository.PessoaRepository;
import com.fishcount.api.service.generic.AbstractMockService;
import com.fishcount.api.service.impl.PessoaServiceImpl;
import com.fishcount.api.validators.PessoaValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.NumericUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PessoaServiceImplTest extends AbstractMockService {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaValidator pessoaValidator;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ControleEmailService controleEmailService;

    @Mock
    private EmailService emailService;

    @Mock
    private TelefoneService telefoneService;

    private Pessoa pessoaMock;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaMock = PessoaMock.criarMock();
    }

    @Test
    @DisplayName("Validando dados da pessoa na inserção")
    void testarInsercao_DadosPessoaPreenchidos() {
        final Pessoa pessoa = pessoaService.incluir(pessoaMock);

        assertNotNull(pessoa.getNome());
        assertNotNull(pessoa.getDataInclusao());
        assertNotNull(pessoa.getDataAtualizacao());
        assertNotNull(pessoa.getSenha());
        assertEquals(NumericUtil.somenteNumero(pessoaMock.getCpf()), pessoa.getCpf());
        assertTrue(pessoa.isAtivo());
        assertNotEquals(Collections.emptyList(), pessoa.getEmails());
        assertNotEquals(Collections.emptyList(), pessoa.getTelefones());
        assertNotEquals(Collections.emptyList(), pessoa.getLotes());
    }

    @Test
    @DisplayName("Validando conversão de cpf para somente números")
    void testarInsercao_CpfSomenteNumero() {
        final Pessoa pessoa = pessoaService.incluir(pessoaMock);

        assertEquals("00000000000", pessoa.getCpf());
    }

    @Test
    @DisplayName("Validando pessoa não encontrada por CPF")
    void testarBuscaCpf_CpfNaoEncontrado() {
        when(pessoaRepository.findByCpf(pessoaMock.getCpf()))
                .thenReturn(null);

        final String cpf = pessoaMock.getCpf();
        String message = assertThrows(FcRuntimeException.class,
                () -> pessoaService.encontrarPessoaByCpf(cpf))
                .getMessage();

        assertEquals(message, getException(EnumFcDomainException.PESSOA_NAO_ENCONTRADA_POR_CPF, pessoaMock.getCpf()));
    }

    @Test
    @DisplayName("Validando busca de pessoa por CPF")
    void testarBuscaCpf_CpfEncontrado() {
        when(pessoaRepository.findByCpf(pessoaMock.getCpf()))
                .thenReturn(pessoaMock);

        assertEquals(pessoaMock, pessoaService.encontrarPessoaByCpf(pessoaMock.getCpf()));
    }

    @Test
    @DisplayName("Validando dados do telefone para inserção")
    void testarInsercao_ValidarDadosTelefone() {
        pessoaService.incluir(pessoaMock);

        final Telefone telefone = ListUtil.first(pessoaMock.getTelefones());

        assertNotNull(telefone);
        assertNotNull(telefone.getDataInclusao());
        assertNotNull(telefone.getDataAtualizacao());
        assertTrue(telefone.isAtivo());
        assertEquals(pessoaMock, telefone.getPessoa());

        verify(telefoneService, times(1))
                .validarTelefones(pessoaMock);
    }

    @Test
    @DisplayName("Validando dados do email para inserção")
    void testarInsercao_ValidarDadosEmail() {
        pessoaService.incluir(pessoaMock);

        final Email email = ListUtil.first(pessoaMock.getEmails());

        assertNotNull(email);
        assertNotNull(email.getDataInclusao());
        assertNotNull(email.getDataAtualizacao());
        assertEquals(email.getDataInclusao(), email.getDataAtualizacao());
        assertTrue(email.isAtivo());
        assertEquals(pessoaMock, email.getPessoa());

        verify(emailService, times(1))
                .validarEmails(pessoaMock);
    }

    @Test
    @DisplayName("Verificando se usuário esta sendo gerado na inserção de pessoa")
    void testarInsercao_EstaInserindoUsuario() {
        pessoaService.incluir(pessoaMock);

        verify(usuarioService, times(1))
                .gerarUsuario(pessoaMock);
    }

    @Test
    @DisplayName("Verificando se esta sendo enviando email de confirmação de email para o usuário")
    void testarInsercao_EmailDeConfirmacaoDeEmailEstaSendoEnviado() {
        pessoaService.incluir(pessoaMock);

        verify(controleEmailService, times(1))
                .enviarEmail(pessoaMock, EnumTipoEnvioEmail.CONFIRMACAO_NOVO_CADASTRO, false);
    }
}
