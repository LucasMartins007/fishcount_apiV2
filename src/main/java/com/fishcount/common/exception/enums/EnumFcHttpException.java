package com.fishcount.common.exception.enums;

import com.fishcount.common.exception.IFcException;

public enum EnumFcHttpException implements IFcException {

    NAO_FOI_POSSIVEL_CAPTURAR_TOKEN_PIX("Ocorreu um erro nos servidores de controle de pix, por favor entre em contato -> TokenPix"),
    RETORNO_INESPERADO_TOKEN_PIX("Ocorreu um erro nos servidores de controle de pix, por favor entre em contato -> RetornoTokenPix"),
    ;

    private final String message;

    EnumFcHttpException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
