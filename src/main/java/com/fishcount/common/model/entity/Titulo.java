package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
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
@Table(name = "titulo")
public class Titulo extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_titulo", sequenceName = "gen_id_titulo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_titulo")
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inclusao")
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_alteracao")
    private Date dataAlteracao;
    
    @Column(name = "qtde_parcelas")
    private Integer qtdeParcelas;

    @OneToMany(mappedBy = "titulo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TituloParcela> titulosParcelas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_titulo_to_usuario"))
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plano", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_titulo_to_plano"))
    private Plano plano;

}
