package com.fishcount.api.service;

import com.fishcount.api.mock.EmailMock;
import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.mock.UsuarioMock;
import com.fishcount.api.repository.UsuarioRepository;
import com.fishcount.api.service.impl.UsuarioServiceImpl;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.api.validators.UsuarioValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private UsuarioValidator usuarioValidator;

    @Mock
    private PasswordEncoder passwordEncoder;


    private Usuario usuarioMock;

    private Email emailMock;

    private Pessoa pessoaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaMock = PessoaMock.criarMock();
        usuarioMock = UsuarioMock.criarMock(pessoaMock);
        emailMock = EmailMock.criarMock(pessoaMock);
    }

    @Test
    @DisplayName("Testar inclusão, dados do usuário estão sendo preparados para inclusão.")
    void testarInclusao_PreparacaoDeDadosParaInsercao() {
        when(usuarioRepository.save(usuarioMock))
                .thenReturn(usuarioMock);

        when(passwordEncoder.encode(usuarioMock.getSenha()))
                .thenReturn(usuarioMock.getSenha());

        final Usuario usuario = usuarioService.incluir(usuarioMock);
        assertTrue(usuario.isAtivo());
        assertEquals(emailMock.getDescricao(), usuario.getEmail(),
                "Email do usuário deve ser o mesmo email cadastrado para pessoa.");
        assertEquals(pessoaMock.getNome(), usuario.getNome(),
                "Nome do usuário deve ser o mesmo que o nome da Pessoa.");
        assertNotNull(usuario.getDataAtualizacao());
        assertNotNull(usuario.getDataInclusao());
        assertNotNull(usuario.getSenha());
    }

    @Test
    @DisplayName("Testar inclusão, senha do usuário está sendo criptografada.")
    void testarInclusao_SenhaEstaSendoCriptografada() {
        when(passwordEncoder.encode(usuarioMock.getSenha()))
                .thenReturn(usuarioMock.getSenha());

        usuarioService.incluir(usuarioMock);

        verify(passwordEncoder, times(1))
                .encode(usuarioMock.getSenha());
    }

    @Test
    @DisplayName("Testar busca por email, usuário encontrado.")
    void testarBuscaPorEmail_UsuarioEncontrado() {
        when(usuarioRepository.findByEmailPrincipal(usuarioMock.getEmail()))
                .thenReturn(usuarioMock);

        assertDoesNotThrow(() ->
                usuarioService.findByEmail(usuarioMock.getEmail()),
                "Nao deve retornar exceção caso um email for encontrado.");
    }


    @Test
    @DisplayName("Testar busca por email, usuário não encontrado.")
    void testarBuscaPorEmail_UsuarioNaoEncontrado() {
        when(usuarioRepository.findByEmailPrincipal(usuarioMock.getEmail()))
                .thenReturn(null);

        assertThrows(FcRuntimeException.class, () ->
                        usuarioService.findByEmail(usuarioMock.getEmail()),
                "Deve retornar exceção caso um email não for encontrado.");
    }

    @Test
    @DisplayName("Testar busca por id, usuário encontrado.")
    void testarBuscaPorId_UsuarioEncontrado() {
        when(usuarioRepository.findById(usuarioMock.getId()))
                .thenReturn(Optional.of(usuarioMock));

        assertDoesNotThrow(() -> usuarioService.encontrarPorId(usuarioMock.getId()),
                "Caso usuario for encontrado, não deve retornar exceção.");
    }

    @Test
    @DisplayName("Testar busca por id, usuário não encontrado.")
    void testarBuscaPorID_UsuarioNaoEncontrado() {
        when(usuarioRepository.findById(usuarioMock.getId()))
                .thenReturn(Optional.empty());

        assertThrows(FcRuntimeException.class,
                () -> usuarioService.encontrarPorId(usuarioMock.getId()),
                "Caso usuário não for encontrado deve retornar uma exceção.");
    }

    @Test
    @DisplayName("Testar geração de usuário, Dados obrigatórios estão sendo setados.")
    void testarGeracaoUsuario_ValidarDadosObrigatorios() {
        when(emailService.encontrarEmailPrincipal(pessoaMock))
                .thenReturn(emailMock);
        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuarioMock);

        final Usuario usuario = usuarioService.gerarUsuario(pessoaMock);
        assertEquals(pessoaMock.getNome(), usuario.getNome());
        assertEquals(pessoaMock.getSenha(), usuario.getSenha());
        assertEquals(emailMock.getDescricao(), usuario.getEmail());
        assertTrue(usuario.isAtivo());
    }

    @Test
    @DisplayName("Testar geração de usuário, esta sendo consultado se o um email principal já existe no sistema.")
    void testarGeracaoUsuario_EstaSendoConsultadoEmail() {
        when(emailService.encontrarEmailPrincipal(pessoaMock))
                .thenReturn(emailMock);

        usuarioService.gerarUsuario(pessoaMock);
        verify(emailService, times(1))
                .encontrarEmailPrincipal(pessoaMock);
    }

    @Test
    @DisplayName("Testar geração de usuário, usuario está sendo salvo no final do processo.")
    void testarGeracaoUsuario_UsuarioEstaSendoSalvo() {
        when(emailService.encontrarEmailPrincipal(pessoaMock))
                .thenReturn(emailMock);

        usuarioService.gerarUsuario(pessoaMock);
        verify(usuarioRepository, times(1))
                .save(any(Usuario.class));
    }


}
