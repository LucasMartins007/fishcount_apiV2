package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.PessoaController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.PessoaService;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Martins
 */
@RestController
@RequiredArgsConstructor
public class PessoaControllerImpl
        extends AbstractController<PessoaService>
        implements PessoaController {

    private final PessoaService pessoaService;

    @Override
    public PessoaDTO incluir(PessoaDTO pessoaDTO) {
        final Pessoa pessoa = converterDTOParaEntity(pessoaDTO, Pessoa.class);

        return converterEntityParaDTO(pessoaService.incluir(pessoa), PessoaDTO.class);
    }

    @Override
    public PessoaDTO encontrar(Integer id) {
        return converterEntityParaDTO(pessoaService.encontrarPessoa(id), PessoaDTO.class);
    }

    @Override
    public void adicionarPessoaFisica(Integer pessoaId, String cpf) {
        pessoaService.adicionarPessoaFisica(pessoaId, cpf);
    }

    @Override
    public void atualizar(Integer id, PessoaDTO pessoaDTO) {
        pessoaService.atualizar(id, converterDTOParaEntity(pessoaDTO, Pessoa.class));
    }
}
