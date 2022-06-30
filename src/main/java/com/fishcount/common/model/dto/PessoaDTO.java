package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.pattern.annotations.converter.TransientFieldDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PessoaDTO implements AbstractDTO<Integer> {

    private Integer id;

    private String nome;

    private String cpf;

    @TransientFieldDTO
    private String senha;

    private boolean ativo;

    private List<TelefoneDTO> telefones;

    private List<EmailDTO> emails;

    private List<EnderecoDTO> enderecos;

    private List<LoteDTO> lotes;

    private Date dataInclusao;

    private Date daraAlteracao;
}
