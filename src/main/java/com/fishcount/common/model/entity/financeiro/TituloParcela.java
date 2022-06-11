package com.fishcount.common.model.entity.financeiro;

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

import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import com.fishcount.common.model.pattern.AbstractEntity;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "fin_titulo_parcela")
public class TituloParcela extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_titulo_parcela", sequenceName = "gen_id_fin_titulo_parcela")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_titulo_parcela")
    private Integer id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "acrescimo")
    private BigDecimal acrescimo;

    @Column(name = "desconto")
    private BigDecimal desconto;

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;

    @Column(name = "status_titulo")
    @Convert(converter = EnumStatusTitulo.EnumConverter.class)
    private EnumStatusTitulo statusTitulo;

    @Column(name = "tipo_titulo")
    @Convert(converter = EnumTipoTitulo.EnumConverter.class)
    private EnumTipoTitulo tipoTitulo;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_titulo_parcela_to_fin_titulo"))
    private Titulo titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pagamento_parcela", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_titulo_parcela_to_fin_pagamento_parcela"))
    private PagamentoParcela pagamentoParcela;
}
