package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControleEmailDTO implements AbstractDTO<Integer> {

    private Integer id;

    private String remetente;

    private String destinatario;

    private String assunto;

    private String corpo;

    private EnumTipoEnvioEmail tipoEnvioEmail;

}
