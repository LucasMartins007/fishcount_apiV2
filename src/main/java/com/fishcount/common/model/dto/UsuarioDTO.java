package com.fishcount.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.pattern.annotations.converter.OnlyField;
import com.fishcount.common.model.pattern.annotations.converter.TransientFieldDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author lucas
 */
@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements AbstractDTO<Integer> {

    private Integer id;

    private String nome;

    @OnlyField({"id", "descricao", "tipoTelefone"})
    private List<TelefoneDTO> telefones;

    @OnlyField({"id", "descricao", "tipoEmail"})
    private List<EmailDTO> emails;

    @TransientFieldDTO
    private String senha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInclusao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataAlteracao;


}
