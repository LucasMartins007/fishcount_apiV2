package com.fishcount.common.model.entity.financeiro;

import com.fishcount.common.model.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "fin_plano")
public class Plano extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_plano", sequenceName = "gen_id_fin_plano")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_plano")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_minimo")
    private BigDecimal valorMinimo;

    @Column(name = "valor_maximo")
    private BigDecimal valorMaximo;

    @Column(name = "min_taque")
    private Integer minTanque;

    @Column(name = "max_taque")
    private Integer maxTanque;

    @Column(name = "qtde_parcela")
    private Integer qtdeParcela;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "data_inclusao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @PrePersist
    private void prePersist() {
        this.dataInclusao = DateUtil.getDate();
        this.dataAtualizacao = DateUtil.getDate();
        this.setAtivo(true);
    }

    @PreUpdate
    private void preUpdate() {
        this.dataAtualizacao = DateUtil.getDate();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
