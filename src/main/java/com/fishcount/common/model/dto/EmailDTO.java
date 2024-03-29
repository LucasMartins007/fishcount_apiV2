package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumTipoEmail;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class EmailDTO implements AbstractDTO<Integer> {

    private Integer id;

    private String descricao;

    private boolean ativo;

    private EnumTipoEmail tipoEmail;

    private Date dataInclusao;

    private Date dataAtualizacao;

}
