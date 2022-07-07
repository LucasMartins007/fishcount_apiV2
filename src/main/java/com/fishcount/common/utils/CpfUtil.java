package com.fishcount.common.utils;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CpfUtil {

    private static final Integer CPF_DIGITS = 11;

    public static boolean isValid(String value) {
        if (StringUtil.isNullOrEmpty(value)) {
            return false;
        }

        value = StringUtil.keepOnlyNumbers(value);

        // fim do bloco.
        if (value.length() == CPF_DIGITS) {
            // Verifica se os digitos informados são identicos...
            if ((value.compareTo("99999999999") == 0)
                    || (value.compareTo("00000000000") == 0)
                    || (value.compareTo("11111111111") == 0)
                    || (value.compareTo("22222222222") == 0)
                    || (value.compareTo("33333333333") == 0)
                    || (value.compareTo("44444444444") == 0)
                    || (value.compareTo("55555555555") == 0)
                    || (value.compareTo("66666666666") == 0)
                    || (value.compareTo("77777777777") == 0)
                    || (value.compareTo("88888888888") == 0)) {
                // se forem, atribui 1 ao teste e abaixo e exibida a mensagem de
                // erro
                return false;
            }

            // Inicia o cálculo do módulo do primeiro digito verificador do
            // CPF
            int primeiroDivisor = 0;
            int priNum = Integer.parseInt(value.substring(0, 1));
            int segNum = Integer.parseInt(value.substring(1, 2));
            int terNum = Integer.parseInt(value.substring(2, 3));
            int quaNum = Integer.parseInt(value.substring(3, 4));
            int quiNum = Integer.parseInt(value.substring(4, 5));
            int sexNum = Integer.parseInt(value.substring(5, 6));
            int setNum = Integer.parseInt(value.substring(6, 7));
            int oitNum = Integer.parseInt(value.substring(7, 8));
            int nonNum = Integer.parseInt(value.substring(8, 9));
            int soma = (priNum * 10) + (segNum * 9) + (terNum * 8)
                    + (quaNum * 7) + (quiNum * 6) + (sexNum * 5)
                    + (setNum * 4) + (oitNum * 3) + (nonNum * 2);
            int resultado = 11 - (soma % 11);

            if ((resultado == 10) || (resultado == 11)) {
                primeiroDivisor = 0;
            } else {
                primeiroDivisor = resultado;
            }

            // Inicia o cálculo do módulo do segundo digito verificador do
            // CPF
            int segundoDivisor;
            int priNum2 = Integer.parseInt(value.substring(0, 1));
            int segNum2 = Integer.parseInt(value.substring(1, 2));
            int terNum2 = Integer.parseInt(value.substring(2, 3));
            int quaNum2 = Integer.parseInt(value.substring(3, 4));
            int quiNum2 = Integer.parseInt(value.substring(4, 5));
            int sexNum2 = Integer.parseInt(value.substring(5, 6));
            int setNum2 = Integer.parseInt(value.substring(6, 7));
            int oitNum2 = Integer.parseInt(value.substring(7, 8));
            int nonNum2 = Integer.parseInt(value.substring(8, 9));
            int soma2 = (priNum2 * 11) + (segNum2 * 10) + (terNum2 * 9)
                    + (quaNum2 * 8) + (quiNum2 * 7) + (sexNum2 * 6)
                    + (setNum2 * 5) + (oitNum2 * 4) + (nonNum2 * 3)
                    + (primeiroDivisor * 2);
            int resultado2 = 11 - (soma2 % 11);

            if ((resultado2 == 10) || (resultado2 == 11)) {
                segundoDivisor = 0;
            } else {
                segundoDivisor = resultado2;
            }

            // concatena o primeiro com o segundo digito e testa se eles
            // estão corretos...
            String verDv = Integer.toString(primeiroDivisor) + Integer.toString(segundoDivisor);

            return verDv.compareTo(value.substring(9, 11)) == 0;
        }

        return false;
    }


    public static boolean isCpf(String value) {
        if (StringUtil.isNotNullOrEmpty(value)) {
            value = StringUtil.keepOnlyNumbers(value);
            return value.length() == CPF_DIGITS;
        }

        return false;
    }

    public static void validate(String cpf) {
        if (!isValid(cpf)) {
            throw new FcRuntimeException(EnumFcDomainException.CPF_INVALIDO, cpf);
        }
    }

    public static String format(String cpf) {
        if (cpf == null) {
            return cpf;
        }

        cpf = StringUtil.keepOnlyNumbers(cpf);

        return Utils.formatter(cpf, "###.###.###-##");
    }

}

