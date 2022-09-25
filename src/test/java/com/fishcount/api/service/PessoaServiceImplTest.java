package com.fishcount.api.service;

import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.mock.UsuarioMock;
import com.fishcount.api.repository.PessoaRepository;
import com.fishcount.api.service.impl.PessoaServiceImpl;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.api.validators.PessoaValidator;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.ListUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class PessoaServiceImplTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaValidator pessoaValidator;

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private TelefoneValidator telefoneValidator;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ControleEmailService ControleEmailService;

    @Mock
    private EmailService emailService;

    private Pessoa pessoaMock;

    private Usuario usuarioMock;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaMock = PessoaMock.criarMock();
        usuarioMock = UsuarioMock.criarMock(pessoaMock);
    }

    @Test
    void testarInsercao_CpfSomenteNumero() {
        final Pessoa pessoa = pessoaService.incluir(pessoaMock);

        Assertions.assertEquals("00000000000", pessoa.getCpf());
    }

    @Test
    void testarInsercao_EmailEstaSendoValidado() {
        pessoaService.incluir(pessoaMock);

        final Email email = ListUtil.first(pessoaMock.getEmails());

        verify(emailValidator, times(1)).validateInsertOrUpdate(email);
    }
    @Test
    void testarAtualizacao_EmailEstaSendoPreparado() {
        final Email email = ListUtil.first(pessoaMock.getEmails());
        Assertions.assertNotNull(email);

        email.setId(1);
        pessoaService.incluir(pessoaMock);

        verify(emailService, times(1)).onPrepareUpdate(1, email);
    }

    @Test
    void testarInsercao_EstaInserindoUsuario() {
        pessoaService.incluir(pessoaMock);

        verify(usuarioService, times(1))
                .incluir(Mockito.any(Usuario.class));
    }
}
