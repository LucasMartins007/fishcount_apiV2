package com.fishcount.api.mock;

import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.utils.DateUtil;

import java.util.Collections;

public class LoteMock {

    private static final String DESCRICAO_LOTE_MOCK = "Lote teste";

    public static Lote criarMock(Pessoa pessoa) {
        final Lote lote = new Lote();

        lote.setId(1);
        lote.setAtivo(true);
        lote.setDataAtualizacao(DateUtil.getDate());
        lote.setDataInclusao(DateUtil.getDate());
        lote.setDescricao(DESCRICAO_LOTE_MOCK);
        lote.setTanques(Collections.singletonList(TanqueMock.criarMock()));
        lote.setPessoa(pessoa);

        return lote;
    }
}
