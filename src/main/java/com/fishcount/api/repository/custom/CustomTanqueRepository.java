package com.fishcount.api.repository.custom;

import com.fishcount.common.model.entity.Tanque;

import java.util.List;

public interface CustomTanqueRepository {

    List<Tanque> findAllByPessoaAndLote(Integer pessoaId, Integer loteId);

    Tanque findByPessoaAndLoteAndId(Integer pessoaId, Integer loteId, Integer tanqueId);
}
