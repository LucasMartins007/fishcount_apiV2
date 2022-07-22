package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.PessoaController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.PessoaService;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Martins
 */
@RestController
public class PessoaControllerImpl
        extends AbstractController<PessoaService>
        implements PessoaController {

    @Override
    public PessoaDTO incluir(PessoaDTO pessoaDTO) {
        Pessoa pessoa = converterDTOParaEntity(pessoaDTO, Pessoa.class);

        return converterEntityParaDTO(getService().incluir(pessoa), PessoaDTO.class);
    }

    @Override
    public PessoaDTO encontrar(Integer id) {
        return converterEntityParaDTO(getService().encontrarPessoa(id), PessoaDTO.class);
    }

    @Override
    public void atualizar(Integer id, PessoaDTO pessoaDTO) {
        getService().atualizar(id, converterDTOParaEntity(pessoaDTO, Pessoa.class));
    }
}
