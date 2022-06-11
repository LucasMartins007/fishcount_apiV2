package com.fishcount.common.model.entity.financeiro;

import com.fishcount.common.model.pattern.AbstractEntity;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @SequenceGenerator(name = "id_fin_plano", sequenceName = "gen_fin_id_plano")
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

    @Column(name = "data_alteracao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

}
