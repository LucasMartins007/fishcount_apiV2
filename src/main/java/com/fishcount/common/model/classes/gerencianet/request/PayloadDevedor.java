package com.fishcount.common.model.classes.gerencianet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
@AllArgsConstructor
public class PayloadDevedor {

    private String nome;
    
    private String cpf;
    
}
