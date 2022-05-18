package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "titulo_parcela")
public class TituloParcela extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_titulo_parcela", sequenceName = "gen_id_titulo_parcela")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_titulo_parcela")
    private Integer id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "acrescimo")
    private Double acrescimo;

    @Column(name = "desconto")
    private Double desconto;

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;

    @Column(name = "status_titulo")
    @Convert(converter = EnumStatusTitulo.EnumConverter.class)
    private EnumStatusTitulo statusTitulo;

    @Column(name = "tipo_titulo")
    @Convert(converter = EnumTipoTitulo.EnumConverter.class)
    private EnumTipoTitulo tipoTitulo;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_titulo_parcela_to_titulo"))
    private Titulo titulo;
}
