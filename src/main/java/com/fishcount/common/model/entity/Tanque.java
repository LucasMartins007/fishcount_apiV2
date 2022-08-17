package com.fishcount.common.model.entity;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
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
@Table(name = "fish_tanque")
public class Tanque extends AbstractEntity<Integer> {
  
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_tanque", sequenceName = "gen_id_fish_tanque")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_tanque")
    private Integer id;
    
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "qtde_peixe")
    private Integer qtdePeixe;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_tanque_id_fish_especie"))
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
    @JoinColumn(name = "id_lote", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_tanque_to_fish_lote"))
    private Lote lote;
    
    @Column(name = "analise")
    @OneToMany(mappedBy = "tanque", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Analise> analise;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @PrePersist
    private void prePersist() {
        this.dataInclusao = DateUtil.getDate();
        this.dataAtualizacao = DateUtil.getDate();
        this.ativo = true;
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