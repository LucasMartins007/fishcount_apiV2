package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.model.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "fish_email")
public class Email extends AbstractEntity<Integer> {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_email", sequenceName = "gen_fish_id_email", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_email")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "tipo_email")
    @Convert(converter = EnumTipoEmail.EnumConverter.class)
    private EnumTipoEmail tipoEmail;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_email_to_fish_pessoa"))
    private Pessoa pessoa;

    @PrePersist
    private void prePersist(){
        this.dataInclusao = DateUtil.getDate();
        this.dataAtualizacao = DateUtil.getDate();
        this.setAtivo(true);
    }

    @PreUpdate
    private void preUpdate(){
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
