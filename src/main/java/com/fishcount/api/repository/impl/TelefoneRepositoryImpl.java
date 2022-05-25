package com.fishcount.api.repository.impl;

import com.fishcount.api.infrastructure.spec.TelefoneSpec;
import com.fishcount.api.repository.custom.CustomTelefoneRepository;
import com.fishcount.api.repository.dao.GenericImpl;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import java.util.List;
import org.springframework.stereotype.Repository;

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
    public Telefone findByUsuarioAndTipo(Usuario usuario, EnumTipoTelefone tipoTelefone) {
        return getSpecRepository()
                .findOne(
                        TelefoneSpec.telefonesAtivos()
                                .and(TelefoneSpec.telefonesByUsuario(usuario))
                                .and(TelefoneSpec.telefoneByTipo(tipoTelefone))
                )
                .orElse(null);
    }

    @Override
    public List<Telefone> findAllAtivosByUsuario(Usuario usuario) {
        return getSpecRepository()
                .findAll(
                        TelefoneSpec.telefonesAtivos()
                                .and(TelefoneSpec.telefonesByUsuario(usuario))
                );
    }

}
