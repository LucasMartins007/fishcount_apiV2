package com.fishcount.common.model.entity;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.model.enums.EnumTipoTelefone;
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
@Table(name = "fish_telefone")
public class Telefone extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_telefone", sequenceName = "gen_id_fish_telefone", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_telefone")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo_telefone")
    @Convert(converter = EnumTipoTelefone.EnumConverter.class)
    private EnumTipoTelefone tipoTelefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_telefone_to_fish_pessoa"))
    private Pessoa pessoa;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
