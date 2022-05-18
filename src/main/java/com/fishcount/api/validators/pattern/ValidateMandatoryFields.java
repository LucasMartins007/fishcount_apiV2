package com.fishcount.api.validators.pattern;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.IFcException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.NumericUtil;
import com.fishcount.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author lucas
 */
public class ValidateMandatoryFields {

    private final List<String> nullFields = new ArrayList<>();

    public void add(Object valor, String nome) {
        boolean isNullOrEmpty;
        if (valor instanceof Collection<?>) {
            isNullOrEmpty = ListUtil.isNullOrEmpty((Collection<?>) valor);
        } else if (valor instanceof CharSequence) {
            isNullOrEmpty = StringUtil.isNullOrEmpty(valor);
        } else if (valor != null && valor.getClass().isArray()) {
            isNullOrEmpty = ListUtil.isNullOrEmpty((Object[]) valor);
        } else {
            isNullOrEmpty = valor == null;
        }

        if (isNullOrEmpty) {
            nullFields.add(nome);
        }
    }

    public <T> void add(String nome, T valor, Predicate<? super T> predicateAdd) {
        if (predicateAdd.test(valor)) {
            nullFields.add(nome);
        }
    }

    public void addValor(Number valor, String nome) {
        if (valor == null || NumericUtil.isLessOrEquals(valor, 0)) {
            nullFields.add(nome);
        }
    }

    public void validate() {
        validate(EnumFcDomainException.CAMPOS_OBRIGATORIOS);
    }

    public void validate(IFcException messageTemplate) {
        if (ListUtil.isNotNullOrNotEmpty(this.nullFields)) {
            throw new FcRuntimeException(messageTemplate, this.nullFields);
        }
    }

}
