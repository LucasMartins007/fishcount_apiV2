package com.fishcount.common.model.classes.gerencianet;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PayloadCalendario {

    public PayloadCalendario(Date criacao, Integer expiracao) {
        this.criacao = criacao;
        this.expiracao = expiracao;
    }

    private Date criacao;

    private Integer expiracao;

    private Date apresentacao;

}
