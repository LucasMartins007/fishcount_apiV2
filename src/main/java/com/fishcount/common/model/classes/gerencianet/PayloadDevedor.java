package com.fishcount.common.model.classes.gerencianet;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PayloadDevedor {

    public PayloadDevedor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    private String nome;
    
    private String cpf;
    
}
