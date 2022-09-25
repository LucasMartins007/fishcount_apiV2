package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import com.fishcount.common.utils.DateUtil;

public class TelefoneMock {

    private static String TELEFONE = "55998281323";
    public static Telefone criarMock(Pessoa pessoa) {
        Telefone telefone = new Telefone();
        telefone.setAtivo(true);
        telefone.setDataInclusao(DateUtil.getDate());
        telefone.setDataAtualizacao(DateUtil.getDate());
        telefone.setTipoTelefone(EnumTipoTelefone.PRINCIPAL);
        telefone.setDescricao(TELEFONE);
        telefone.setPessoa(pessoa);

        return telefone;
    }
}
