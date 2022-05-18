package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.validation.constraints.Email;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class TelefoneDTO extends AbstractDTO<Integer> {

    private Integer id;
  
    private String descricao;

    private boolean ativo;

    private EnumTipoTelefone tipoTelefone;

    private Date dataInclusao;

    private Date dataAtualizacao;

}
