package com.fishcount.common.model.classes.gerencianet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PayloadCalendario {

    public PayloadCalendario(Integer expiracao) {
        this.expiracao = expiracao;
    }

    private Integer expiracao;

}
