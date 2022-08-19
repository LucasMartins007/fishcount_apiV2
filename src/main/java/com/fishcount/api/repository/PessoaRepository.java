package com.fishcount.api.repository;

import com.fishcount.api.repository.custom.CustomPessoaRepository;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, JpaSpecificationExecutor<Pessoa>, CustomPessoaRepository {

    @Override
    Pessoa findByCpf(String cpf);

    @Override
    Pessoa findByUsuario(Usuario usuario);
}
