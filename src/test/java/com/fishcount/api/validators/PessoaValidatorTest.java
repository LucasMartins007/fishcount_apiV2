package com.fishcount.api.validators;

import com.fishcount.api.mock.PessoaMock;
import com.fishcount.api.repository.PessoaRepository;
import com.fishcount.api.service.gerencianet.pix.generic.AbstractMock;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.utils.ListUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class PessoaValidatorTest extends AbstractMock {

    @InjectMocks
    private PessoaValidator pessoaValidator;

    @Mock
    private PessoaRepository pessoaRepository;

    private Pessoa pessoaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaMock = PessoaMock.criarMock();
    }

    @Test
    @DisplayName("Validar campo nome vazio.")
    void testarValidacao_ValidarCamposNomeVazio() {
        pessoaMock.setNome("");
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getMessage();

        final List<String> details = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getDetails();
        assertEquals(EnumFcDomainException.CAMPOS_OBRIGATORIOS.getMessage(), message);
        assertEquals(ListUtil.toList("Nome"), details);
    }

    @Test
    @DisplayName("Validar campo nome nulo.")
    void testarValidacao_ValidarCamposNomeNulo() {
        pessoaMock.setNome(null);
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getMessage();

        final List<String> details = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getDetails();
        assertEquals(EnumFcDomainException.CAMPOS_OBRIGATORIOS.getMessage(), message);
        assertEquals(ListUtil.toList("Nome"), details);
    }

    @Test
    @DisplayName("Validar campo email nulo.")
    void testarValidacao_ValidarCamposEmailNulo() {
        pessoaMock.setEmails(null);
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getMessage();

        final List<String> details = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getDetails();
        assertEquals(EnumFcDomainException.CAMPOS_OBRIGATORIOS.getMessage(), message);
        assertEquals(ListUtil.toList("Email"), details);
    }

    @Test
    @DisplayName("Validar campo email vazio.")
    void testarValidacao_ValidarCamposEmailVazio() {
        pessoaMock.setEmails(Collections.emptyList());
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getMessage();

        final List<String> details = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getDetails();
        assertEquals(EnumFcDomainException.CAMPOS_OBRIGATORIOS.getMessage(), message);
        assertEquals(ListUtil.toList("Email"), details);
    }

    @Test
    @DisplayName("Validar campo telefone nulo.")
    void testarValidacao_ValidarCamposTelefoneNulo() {
        pessoaMock.setTelefones(null);
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getMessage();

        final List<String> details = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getDetails();
        assertEquals(EnumFcDomainException.CAMPOS_OBRIGATORIOS.getMessage(), message);
        assertEquals(ListUtil.toList("Telefone"), details);
    }

    @Test
    @DisplayName("Validar campo telefone vazio.")
    void testarValidacao_ValidarCamposTelefoneVazio() {
        pessoaMock.setTelefones(Collections.emptyList());
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getMessage();

        final List<String> details = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateRequiredFields(pessoaMock)).getDetails();
        assertEquals(EnumFcDomainException.CAMPOS_OBRIGATORIOS.getMessage(), message);
        assertEquals(ListUtil.toList("Telefone"), details);
    }

    @Test
    @DisplayName("Validar campos obrigatórios nulos os vazios lançandos exceções.")
    void testarValidacao_ValidarInsert() {
        pessoaMock.setNome(null);
        pessoaMock.setEmails(null);
        pessoaMock.setTelefones(null);
        pessoaMock.setSenha(null);
        assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateInsert(pessoaMock));

        pessoaMock.setNome("");
        pessoaMock.setEmails(Collections.emptyList());
        pessoaMock.setTelefones(Collections.emptyList());
        pessoaMock.setSenha("");
        assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateInsert(pessoaMock));
    }

    @Test
    @DisplayName("Validar tamanho do campo nome entre 3 e 50 caracteres.")
    void testarValidacao_ValidarTamanhoCampo() {
        pessoaMock.setNome("a");
        assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateSizeFields(pessoaMock));

        final StringBuilder campoGrandeInvalido = new StringBuilder();
        for (int i = 0; i < 51; i++) {
            campoGrandeInvalido.append("a");
        }
        pessoaMock.setNome(campoGrandeInvalido.toString());
        assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateSizeFields(pessoaMock));
    }

    @Test
    @DisplayName("Testar se esta sendo validado CPF duplicados no update.")
    void testarValidacao_ValidarCPFDuplicado() {
        String CPF_MOCK = "10574732942";
        pessoaMock.setCpf(CPF_MOCK);
        when(pessoaRepository.findByCpf(CPF_MOCK))
                .thenReturn(new Pessoa());

        pessoaMock.setId(1);
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateUpdate(pessoaMock))
                .getMessage();

        assertEquals(EnumFcDomainException.CPF_DUPLICADO.getMessage(), message);
    }

    @Test
    @DisplayName("Testar se esta sendo validado CPF inválidos no update.")
    void testarValidacao_ValidarCPFInvalido() {
        final String message = assertThrows(FcRuntimeException.class,
                () -> pessoaValidator.validateUpdate(pessoaMock))
                .getMessage();

        assertEquals(getException(EnumFcDomainException.CPF_INVALIDO, pessoaMock.getCpf()), message);
    }

}
