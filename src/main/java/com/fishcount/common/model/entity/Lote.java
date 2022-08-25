package com.fishcount.common.model.entity;


import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "fish_lote")
public class Lote extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_lote", sequenceName = "gen_id_fish_lote")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_lote")
    private Integer id;

    @Column(name = "descritpion")
    private String descricao;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @OneToMany(mappedBy = "lote", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tanque> tanques;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_lote_to_fish_pessoa"))
    private Pessoa pessoa;

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
