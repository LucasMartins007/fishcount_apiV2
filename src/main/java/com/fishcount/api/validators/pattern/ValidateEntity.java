package com.fishcount.api.validators.pattern;

import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.pattern.enums.EnumDateFormat;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.NumericUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateEntity {

    public static void validateGreaterThanZero(Number number, String name) {
        if (number != null && number.doubleValue() <= 0) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MENOR_IGUAL_ZERO, name);
        }
    }

    public static void validateLessThan(BigDecimal number, BigDecimal min, String name) {
        if (number != null && number.compareTo(min) >= 0) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MENOR_QUE, name, min);
        }
    }

    public static <N extends Number> void validateLessThan(N number, N min, String name) {
        if (number != null && number.doubleValue() >= min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MENOR_QUE, name, min);
        }
    }

    public static <N extends Number> void validateLessThan(N number, N min, String name, String arg) {
        if (number != null && number.doubleValue() >= min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MENOR_QUE, name, min, arg);
        }
    }

    public static <N extends Number> void validateLessThanOrEqual(N number, N min, String name) {
        if (number != null && number.doubleValue() > min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MENOR_OU_IGUAL_QUE, name, min);
        }
    }

    public static <N extends Number> void validateLessThanOrEqual(N number, N min, String name, String arg) {
        if (number != null && number.doubleValue() > min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MENOR_OU_IGUAL_QUE, name, min, arg);
        }
    }

    public static <N extends Number> void validateGreaterThan(N number, N min, String name) {
        if (number != null && number.doubleValue() <= min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MAIOR_QUE, name, min);
        }
    }

    public static <N extends Number> void validateGreaterThan(N number, N min, String name, String arg) {
        if (number != null && number.doubleValue() <= min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MAIOR_QUE, name, min, arg);
        }
    }

    public static <N extends Number> void validateGreaterThanOrEqual(N number, N min, String name) {
        if (number != null && number.doubleValue() < min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MAIOR_OU_IGUAL_QUE, name, min);
        }
    }

    public static <N extends Number> void validateGreaterThanOrEqual(N number, N min, String name, String arg) {
        if (number != null && number.doubleValue() < min.doubleValue()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MAIOR_OU_IGUAL_QUE, name, min, arg);
        }
    }

    public static <N extends Number> void validateBetween(N value, N min, N max, String name) {
        if (value != null) {
            final Double minimum = NumericUtil.getNotNullOrZero(min.doubleValue());
            final Double maximun = NumericUtil.getNotNullOrZero(max.doubleValue());

            if (value.doubleValue() < minimum || value.doubleValue() > maximun) {
                throw new FcRuntimeException(EnumFcDomainException.CAMPO_ENTRE, name, min, max);
            }
        }
    }

    public static void validateMinCaracter(String value, int min, String name) {
        if (value == null || value.trim().length() < min) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MINIMO_CARACTERS, name, min);
        }
    }

    public static void validateMaxCaracter(String value, int max, String name) {
        if (value != null && value.trim().length() > max) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_MAXIMO_CARACTERS, name, max);
        }
    }

    public static void validateMinMaxCaracter(String value, int min, int max, String name) {
        validateMinCaracter(value, min, name);
        validateMaxCaracter(value, max, name);
    }

    public static void validateMinMaxCaracterIfFieldNotNull(String value, int min, int max, String name) {
        if (value != null && !value.isEmpty()) {
            validateMinCaracter(value, min, name);
            validateMaxCaracter(value, max, name);
        }
    }

    public static void validateDateGreaterThen(Date dataInicial, Date dataFinal) {
        validateDateGreaterThen(dataInicial, dataFinal, EnumDateFormat.DDMMYYYYHHMMSS);
    }

    public static void validateHourGreaterThen(Date dataInicial, Date dataFinal) {
        final EnumDateFormat format = EnumDateFormat.HHMM;
        if (DateUtil.compareTo(dataInicial, dataFinal, format) > 0) {
            throw new FcRuntimeException(EnumFcDomainException.HORA_INICIAL_MAIOR_DATA_FINAL, format.format(dataInicial), format.format(dataFinal));
        }
    }

    public static void validateDateGreaterThen(Date dataInicial, Date dataFinal, EnumDateFormat format) {
        if (DateUtil.compareTo(dataInicial, dataFinal, format) > 0) {
            throw new FcRuntimeException(EnumFcDomainException.DATA_INICIAL_MAIOR_DATA_FINAL, format.format(dataInicial), format.format(dataFinal));
        }
    }

    public static void validateDateGreaterThenCurrentDate(Date dataTest, String name) {
        final Date hoje = DateUtil.getDate();
        if (DateUtil.compareTo(dataTest, hoje, EnumDateFormat.DDMMYYYY) > 0) {
            throw new FcRuntimeException(EnumFcDomainException.DATA_MAIOR_DATA_ATUAL, name, EnumDateFormat.DDMMYYYY.format(dataTest));
        }
    }

    public static void validateDateLessThenCurrentDate(Date dataTest, String name) {
        final Date hoje = DateUtil.getDate();
        if (DateUtil.compareTo(hoje, dataTest, EnumDateFormat.DDMMYYYY) > 0) {
            throw new FcRuntimeException(EnumFcDomainException.DATA_MENOR_DATA_ATUAL, name, EnumDateFormat.DDMMYYYY.format(dataTest));
        }
    }

    public static void validateSizeEqualsCaracter(String value, int size, String name) {
        value = value == null ? "" : value;
        if (value.trim().length() != size) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_IGUAL_CARACTERS, name, size);
        }
    }

    public static <N extends Number> void validateSizeEqualsNumber(N value, int size, String name) {
        if (value != null && size != String.valueOf(value).length()) {
            throw new FcRuntimeException(EnumFcDomainException.CAMPO_IGUAL_CARACTERS, name, size);
        }
    }

    public static void validateOnlyNumbers(String value, String name) {
        if (value != null) {
            value = value.replaceAll("[0-9]", "");
            if (value.length() > 0) {
                throw new FcRuntimeException(EnumFcDomainException.CAMPO_SOMENTE_NUMEROS, name);
            }
        }
    }

    public static void validateRegex(String value, String regex, String name) {
        if (!value.matches(regex)) {
            throw new FcRuntimeException(EnumFcDomainException.TELEFONE_INVALIDO, name);
        }
    }

}
