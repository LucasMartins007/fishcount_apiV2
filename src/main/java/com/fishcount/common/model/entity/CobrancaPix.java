package com.fishcount.common.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fish_cobranca_pix")
public class CobrancaPix extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_cobranca_pix", sequenceName = "gen_id_fish_cobranca_pix")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_cobranca_pix")
    private Integer id;

    @Column(name = "tx_id")
    private String txId;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "chavDevedor")
    private String chaveDevedor;

    @Column(name = "statusCobranca")
    @Convert(converter = EnumStatusCobranca.EnumConverter.class)
    private EnumStatusCobranca statusCobranca;

    @Column(name = "observacaoPagador")
    private String observacaoPagador;

    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(name = "data_expiracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExpiracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_cobranca_pix_to_fish_usuario"))
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pagamento", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_pagamento_to_fish_cobranca_pix"))
    private Pagamento pagamento;

}
