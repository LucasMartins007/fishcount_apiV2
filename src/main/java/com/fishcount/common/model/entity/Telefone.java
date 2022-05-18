package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumTipoTelefone;
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
@Table(name = "telefone")
public class Telefone extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_telefone", sequenceName = "gen_id_telefone")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_telefone")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "tipo_telefone")
    @Convert(converter = EnumTipoTelefone.EnumConverter.class)
    private EnumTipoTelefone tipoTelefone;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_telefone_to_usuario"))
    private Usuario usuario;
}
