package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import com.fishcount.common.model.pattern.AbstractEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Entity
@Getter
@Setter
@Table(name = "fish_pagamento_parcela")
public class PagamentoParcela extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_pagamento", sequenceName = "gen_id_fish_pagamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_pagamento")
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
    @Convert(converter = EnumStatusPagamento.EnumConverter.class)
    private EnumTipoPagamento tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo_parcela", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_pagamento_parcela_to_fish_titulo_parcela"))
    private TituloParcela tituloParcela;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pagamento", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_pagamento_parcela_to_fish_pagamento"))
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

}
