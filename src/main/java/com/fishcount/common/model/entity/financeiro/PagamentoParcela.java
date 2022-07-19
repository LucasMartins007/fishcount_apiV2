package com.fishcount.common.model.entity.financeiro;

import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Lucas Martins
 */
@Entity
@Getter
@Setter
@Table(name = "fin_pagamento_parcela")
public class PagamentoParcela extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_pagamento", sequenceName = "gen_id_fin_pagamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_pagamento")
    private Integer id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "desconto")
    private BigDecimal desconto;

    @Column(name = "acrescimo")
    private BigDecimal acrescimo;

    @Column(name = "status_pagamento")
    @Convert(converter = EnumStatusPagamento.EnumConverter.class)
    private EnumStatusPagamento statusPagamento;

    @Column(name = "tipo_pagamento")
    @Convert(converter = EnumTipoPagamento.EnumConverter.class)
    private EnumTipoPagamento tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pagamento", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_pagamento_parcela_to_fin_pagamento"))
    private Pagamento pagamento;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
