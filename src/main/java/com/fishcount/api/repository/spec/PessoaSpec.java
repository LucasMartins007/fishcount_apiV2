package com.fishcount.api.repository.spec;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PessoaSpec {

    private static final String FIELD_CPF = "cpf";

    private static final String FIELD_USUARIO = "usuario";

    public static Specification<Pessoa> byCpf(String cpf) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(FIELD_CPF), cpf);
    }

    public static Specification<Pessoa> byUsuario(Usuario usuario) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(FIELD_USUARIO), usuario);
    }
}
