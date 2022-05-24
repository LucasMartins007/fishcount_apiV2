package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumUnidadeTamanho;
import com.fishcount.common.model.enums.EnumUnidadeTempo;
import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "taxa_crescimento")
public class TaxaCrescimento extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_taxa_crescimento", sequenceName = "gen_id_taxa_crescimento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_taxa_crescimento")
    private Integer id;

    @Column(name = "qtde_aumento")
    private BigDecimal qtdeAumento;

    @Column(name = "unidade_aumento")
    @Convert(converter = EnumUnidadeTamanho.EnumConverter.class)
    private EnumUnidadeTamanho unidadeAumento;

    @Column(name = "intervalo")
    private Integer intervalo;

    @Column(name = "unidade_intervalo")
    @Convert(converter = EnumUnidadeTempo.EnumConverter.class)
    private EnumUnidadeTempo unidadeIntervalo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_taxa_crescimento_to_especie"))
    private Especie especie;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

}
