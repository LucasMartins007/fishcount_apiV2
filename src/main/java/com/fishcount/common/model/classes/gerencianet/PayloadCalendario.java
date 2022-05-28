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

    private Date cricao;

    private Date expiracao;

    private Date apresentacao;

}
