package com.fishcount.api.validators;

import com.fishcount.api.repository.PagamentoRepository;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.entity.financeiro.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.optional.OptionalUtil;

import java.lang.reflect.Array;
import java.util.List;

/**
 *
 * @author Lucas Martins
 */
public class PagamentoValidator extends AbstractValidatorImpl<Pagamento> {

    @Override
    public void validateRequiredFields(Pagamento pagamento) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();
        
        validate.add(pagamento.getValor(), "Valor");
        validate.add(pagamento.getDataVencimento(), "Data de vencimento");
        validate.add(pagamento.getSaldo(), "Saldo");
        validate.add(pagamento.getUsuario(), "Usuário");
        validate.add(pagamento.getTipoPagamento(), "Tipo pagamento");
        
        validate.validate();
    }

    @Override
    public void validateInsert(Pagamento pagamento) {
        validateRequiredFields(pagamento);
        validateUsuarioComPagamentoEmAberto(pagamento);
    }

    private void validateUsuarioComPagamentoEmAberto(Pagamento pagamento){
        final Usuario usuario = pagamento.getUsuario();

        final Pagamento managedPagamento = getRepository(PagamentoRepository.class)
                .findPagamentoByUsuarioAndStatus(usuario, ListUtil.toList(EnumStatusPagamento.ANALISE, EnumStatusPagamento.ABERTO));

        OptionalUtil.ofFallibleNullable(() -> managedPagamento)
                .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.USUARIO_POSSUI_PAGAMENTOS_EM_ABERTO_OU_EM_ANALISE));
    }
    
    

}
