package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.pattern.annotations.converter.OnlyField;
import com.fishcount.common.model.pattern.annotations.converter.TransientFieldDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO extends AbstractDTO<Integer>{
    
    private Integer id;
    
    private String nome;

    @OnlyField({"id", "descricao"})
    private List<TelefoneDTO> telefones;
    
    @OnlyField({"id", "descricao"})
    private List<EmailDTO> emails;
    
    private List<LoteDTO> lotes;
    
    @TransientFieldDTO
    private String senha;
    
    private Date dataInclusao;
    
    private Date dataAlteracao;
    
    
}
