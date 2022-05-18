package com.fishcount.common.model.entity;

import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "analise")
public class Analise extends AbstractEntity<Integer>{
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_analise", sequenceName = "gen_id_analise")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_analise")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tanque", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_analise_to_tanque"))
    private Tanque tanque;

    @Column(name = "qtde_racao")
    private BigDecimal qtdeRacao;

    @Column(name = "peso_medio")
    private BigDecimal pesoMedio;

    @Column(name = "qtde_media_racao")
    private BigDecimal qtdeMediaRacao;

    @Column(name = "data_analise")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnalise;

}
