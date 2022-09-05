package com.fishcount.common.model.entity.financeiro;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "fin_titulo")
public class Titulo extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_titulo", sequenceName = "gen_id_fin_titulo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_titulo")
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
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_titulo_to_fish_pessoa"))
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
