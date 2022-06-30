package com.fishcount.common.model.entity;

import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "fish_analise")
public class Analise extends AbstractEntity<Integer>{
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_analise", sequenceName = "gen_id_fish_analise")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_analise")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tanque", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_analise_to_fish_tanque"))
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
