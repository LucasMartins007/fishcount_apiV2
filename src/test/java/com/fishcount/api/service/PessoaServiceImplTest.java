package com.fishcount.api.service;

import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.repository.PessoaRepository;
import com.fishcount.api.service.impl.PessoaServiceImpl;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.api.validators.PessoaValidator;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.ListUtil;
import org.hibernate.type.ListType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    private Pessoa pessoaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaMock = PessoaMock.criarMock();
    }

    @Test
    void testarInsercao_CpfSomenteNumero() {
        final Pessoa pessoa = pessoaService.incluir(pessoaMock);

        Assertions.assertEquals("00000000000", pessoa.getCpf());
        Assertions.assertEquals("MOCKER", pessoa.getSenha());
        Assertions.assertTrue(pessoa.isAtivo());

        Email email = ListUtil.first(pessoa.getEmails());
    }

    @Test
    void testarInsercao_Senha() {
        final Pessoa pessoa = pessoaService.incluir(pessoaMock);

        final Usuario usuario = pessoa.getUsuario();
        Assertions.assertEquals("MOCKER", usuario.getSenha());
    }
}
