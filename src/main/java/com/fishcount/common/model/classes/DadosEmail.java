package com.fishcount.common.model.classes;

import com.fishcount.common.model.entity.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosEmail {

    private String emailRementente;

    private String nomeDestinatario;

    private String emailDestinatario;

    private String assunto;

    private String corpoEmail;

    private Pessoa pessoa;

}
