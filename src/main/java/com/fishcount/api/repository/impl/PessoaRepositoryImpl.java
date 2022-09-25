package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPessoaRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.api.repository.spec.PessoaSpec;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepositoryImpl extends RepositoryImpl<Pessoa, Integer> implements CustomPessoaRepository {

    @Override
    public Pessoa findByCpf(String cpf) {
        return getSpecRepository()
                .findOne(PessoaSpec.byCpf(cpf))
                .orElse(null);
    }

    @Override
    public Pessoa findByUsuario(Usuario usuario) {
        return getSpecRepository()
                .findOne(PessoaSpec.byUsuario(usuario))
                .orElse(null);
    }
}
