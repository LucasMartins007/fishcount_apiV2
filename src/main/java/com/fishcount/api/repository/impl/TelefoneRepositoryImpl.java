package com.fishcount.api.repository.impl;

import com.fishcount.api.repository.custom.CustomTelefoneRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.api.repository.infrastructure.spec.TelefoneSpec;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@Repository
public class TelefoneRepositoryImpl extends GenericImpl<Telefone, Integer> implements CustomTelefoneRepository {

    @Override
    public Telefone findByDescricao(String descricao) {
        return getSpecRepository()
                .findOne(
                        TelefoneSpec.telefoneByDescricao(descricao))
                .orElse(null);
    }

    @Override
    public Telefone findByPessoaAndTipo(Pessoa pessoa, EnumTipoTelefone tipoTelefone) {
        return getSpecRepository()
                .findOne(
                        TelefoneSpec.telefonesAtivos()
                                .and(TelefoneSpec.telefonesByPessoa(pessoa))
                                .and(TelefoneSpec.telefoneByTipo(tipoTelefone))
                )
                .orElse(null);
    }

    @Override
    public List<Telefone> findAllAtivosByPessoa(Pessoa pessoa) {
        return getSpecRepository()
                .findAll(
                        TelefoneSpec.telefonesAtivos()
                                .and(TelefoneSpec.telefonesByPessoa(pessoa))
                );
    }

}
