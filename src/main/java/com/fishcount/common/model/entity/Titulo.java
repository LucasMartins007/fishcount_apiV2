package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import com.fishcount.common.model.pattern.AbstractEntity;
import java.math.BigDecimal;
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
@Table(name = "fish_titulo")
public class Titulo extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_titulo", sequenceName = "gen_id_fish_titulo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_titulo")
    private Integer id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "acrescimo")
    private BigDecimal acrescimo;

    @Column(name = "desconto")
    private BigDecimal desconto;

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
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_titulo_to_fish_usuario"))
    private Usuario usuario;

}
