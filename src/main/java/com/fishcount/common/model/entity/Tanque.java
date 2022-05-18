package com.fishcount.common.model.entity;

import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "tanque")
public class Tanque extends AbstractEntity<Integer> {
  
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_tank", sequenceName = "gen_id_tank")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_tank")
    private Integer id;
    
    @Column(name = "descricao")
    private String descricao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specie", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tank_id_specie"))
    private Especie especie;
    
    @Column(name = "qtde_ultima_analise")
    private Integer qtdUltimaAnalise;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_proxima_analise")
    private Date dataProximaAnalise;
        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ultima_analise")
    private Date dataUltimaAnalise;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ultimo_tratamento")
    private Date dataUltimoTratamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lote", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tanque_to_lote"))
    private Lote lote;
    
    @Column(name = "analise")
    @OneToMany(mappedBy = "tanque", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Analise> analise;
}