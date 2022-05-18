package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumTipoEmail;
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
@Table(name = "email")
public class Email extends AbstractEntity<Integer> {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_email", sequenceName = "gen_id_email")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_email")
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
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_email_id_usario"))
    private Usuario usuario;
}
