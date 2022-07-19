package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPessoaRepository;
import com.fishcount.api.repository.dao.RepositoryImpl;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepositoryImpl extends RepositoryImpl<Pessoa, Integer> implements CustomPessoaRepository {
}
