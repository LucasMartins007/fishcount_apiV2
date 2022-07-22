package com.fishcount.common.model.entity;

import com.fishcount.common.model.enums.EnumUnidadeTamanho;
import com.fishcount.common.model.enums.EnumUnidadeTempo;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
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
@Table(name = "fish_taxa_crescimento")
public class TaxaCrescimento extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_taxa_crescimento", allocationSize = 1, sequenceName = "gen_id_fish_taxa_crescimento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_taxa_crescimento")
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
    @JoinColumn(name = "id_especie", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_taxa_crescimento_to_fish_especie"))
    private Especie especie;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
