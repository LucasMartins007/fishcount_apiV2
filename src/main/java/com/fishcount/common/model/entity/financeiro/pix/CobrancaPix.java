package com.fishcount.common.model.entity.financeiro.pix;

import com.fishcount.common.model.entity.financeiro.TituloParcela;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fishcount.common.model.enums.EnumStatusCobranca;
import com.fishcount.common.model.pattern.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Entity
@Getter
@Setter
@Table(name = "fin_cobranca_pix")
public class CobrancaPix extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_cobranca_pix", sequenceName = "gen_id_fin_cobranca_pix")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_cobranca_pix")
    private Integer id;

    @Column(name = "tx_id")
    private String txId;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "chave")
    private String chave;

    @Column(name = "status_cobranca")
    @Convert(converter = EnumStatusCobranca.EnumConverter.class)
    private EnumStatusCobranca statusCobranca;

    @Column(name = "observacao_pagador")
    private String observacaoPagador;
    
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @Column(name = "data_expiracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExpiracao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo_parcela", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_cobranca_pix_to_fin_titulo_parcela"))
    private TituloParcela tituloParcela;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_cobranca_pix_to_fin_location"))
    private LocationPix location;
}
