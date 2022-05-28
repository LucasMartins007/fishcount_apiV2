package com.fishcount.common.model.entity;


import com.fishcount.common.model.enums.EnumTipoEndereco;
import com.fishcount.common.model.pattern.AbstractEntity;
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
@Table(name = "fish_endereco")
public class Endereco extends AbstractEntity<Integer> {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_endereco", sequenceName = "gen_id_fish_endereco")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_endereco")
    private Integer id;

    @Column(name = "rua")
    private String rua;

    @Column(name = "estado")
    private String estado;

    @Column(name = "numero")
    private String numero;

    @Column(name = "pais")
    private String pais;

    @Column(name = "tipo_endereco")
    @Convert(converter = EnumTipoEndereco.EnumConverter.class)
    private EnumTipoEndereco tipoEndereco;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_endereco_to_fish_usuario"))
    private Usuario usuario;

}
