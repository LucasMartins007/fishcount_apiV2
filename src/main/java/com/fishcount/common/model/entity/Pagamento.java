package com.fishcount.common.model.entity;

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

import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import com.fishcount.common.model.pattern.AbstractEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "fish_pagamento")
public class Pagamento extends AbstractEntity<Integer> {

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
    @Convert(converter = EnumTipoPagamento.EnumConverter.class)
    private EnumTipoPagamento tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_pagamento_to_fish_usuario"))
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plano", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_pagamento_to_fish_plano"))
    private Plano plano;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_pagamento_to_fish_titulo"))
    private Titulo titulo;

    @OneToMany(mappedBy = "pagamento", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<PagamentoParcela> pagamentoParcelas;
    
    @Column(name = "qtde_parcelas")
    private Integer qtdeParcelas;

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
