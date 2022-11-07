package com.fishcount.api.service.impl;

import com.fishcount.api.repository.PlanoRepository;
import com.fishcount.api.service.ControleEmailService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.service.PlanoService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.PlanoValidator;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoServiceImpl
        extends AbstractServiceImpl<Plano, Integer, PlanoDTO>
        implements PlanoService {

    private final PlanoRepository planoRepository;

    private final PlanoValidator planoValidator;

    private final ControleEmailService controleEmailService;

    private final PessoaService pessoaService;

    @Override
    public List<Plano> listarPlanos() {
        return planoRepository.findAllAtivos();
    }

    @Override
    public Plano incluir(Plano plano) {
        onPrepareInsert(plano);

        return planoRepository.save(plano);
    }

    @Override
    public void enviarEmailContato(Integer planoId, Integer pessoaId) {
        final Plano plano = findAndValidate(planoId);
        final Pessoa pessoa = pessoaService.findAndValidate(pessoaId);

        controleEmailService.enviarEmail(pessoa, EnumTipoEnvioEmail.CONFIRMACAO_NOVO_CADASTRO, true);
    }

    private void onPrepareInsert(Plano plano) {
        planoValidator.validateInsert(plano);
    }

}
