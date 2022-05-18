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
@Table(name = "lote")
public class Lote extends AbstractEntity<Integer> {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_lote", sequenceName = "gen_id_lote")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_lote")
    private Integer id;

    @Column(name = "descritpion")
    private String descricao;
    
    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    
    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_lote_to_usuario"))
    private Usuario usuario;

    @OneToMany(mappedBy = "lote", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tanque> tanques;
}
