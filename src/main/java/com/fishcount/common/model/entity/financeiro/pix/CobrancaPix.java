package com.fishcount.common.model.entity.financeiro.pix;

import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.model.enums.EnumStatusCobranca;
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
    @JoinColumn(name = "id_pagamento_parcela", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_cobranca_pix_to_fin_pagamento_parcela"))
    private PagamentoParcela pagamentoParcela;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_cobranca_pix_to_fin_location"))
    private LocationPix location;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
