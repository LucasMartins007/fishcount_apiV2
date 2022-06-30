package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.Usuario;

/**
 *
 * @author lucas
 */
public class UsuarioValidator extends AbstractValidatorImpl<Usuario> {
    
    @Override
    public void validateRequiredFields(Usuario usuario) {
         ValidateMandatoryFields validate = new ValidateMandatoryFields();
         
         validate.add(usuario.getNome(), "Nome");
         validate.add(usuario.getSenha(), "Senha");
         validate.add(usuario.getEmail(), "Email");

         validate.validate();
    }

    @Override
    public void validateInsert(Usuario usuario) {
        validateRequiredFields(usuario);
    }
}
