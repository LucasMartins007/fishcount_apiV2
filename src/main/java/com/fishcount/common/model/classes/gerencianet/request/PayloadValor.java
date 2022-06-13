package com.fishcount.common.model.classes.gerencianet.request;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PayloadValor {

    public PayloadValor(String original) {
        this.original = original;
    }

    private String original;

}

