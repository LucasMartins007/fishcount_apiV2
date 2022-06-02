package com.fishcount.api.validators;

import com.fishcount.api.repository.TituloRepository;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.utils.optional.OptionalUtil;

/**
 *
 * @author Lucas Martins
 */
public class TituloValidator extends AbstractValidatorImpl<Titulo> {

    @Override
    public void validateRequiredFields(Titulo titulo) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(titulo.getValor(), "Valor");
        validate.add(titulo.getAcrescimo(), "Acrescimo");
        validate.add(titulo.getDesconto(), "Desconto");
        validate.add(titulo.getUsuario(), "Usuario");
        validate.add(titulo.getDataVencimento(), "Data de vencimento");
        validate.add(titulo.getStatusTitulo(), "Status do título");

        validate.validate();
    }

    @Override
    public void validateInsert(Titulo titulo) {
        validateTituloAbertoUsuario(titulo);

    }

    private void validateTituloAbertoUsuario(Titulo titulo) {
        Usuario usuario = titulo.getUsuario();
        OptionalUtil.ofFallibleNullable(() -> getRepository(TituloRepository.class)
                .findByStatusAndUsuario(EnumStatusTitulo.EM_ABERTO, usuario))
                .ifPresentThrow(
                        () -> new FcRuntimeException(EnumFcDomainException.USUARIO_POSSUI_TITULO_ABERTO, usuario.getNome()));
    }

}
