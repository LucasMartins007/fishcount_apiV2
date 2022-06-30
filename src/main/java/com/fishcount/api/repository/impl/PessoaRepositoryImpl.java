package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomPessoaRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepositoryImpl extends GenericImpl<Pessoa, Integer> implements CustomPessoaRepository {
}
