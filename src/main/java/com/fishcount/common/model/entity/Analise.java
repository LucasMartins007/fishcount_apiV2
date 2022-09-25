package com.fishcount.common.model.entity;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "fish_analise")
public class Analise extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_analise", allocationSize = 1, sequenceName = "gen_id_fish_analise")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_analise")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tanque", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_analise_to_fish_tanque"))
    private Tanque tanque;

    @Column(name = "qtde_racao")
    private Integer qtdeRacao;

    @Column(name = "peso_medio_tanque")
    private BigDecimal pesoMedioTanque;

    @Column(name = "tipo_racao")
    private String tipoRacao;

    @Column(name = "temperatura_agua")
    private BigDecimal temperaturaAgua;

    @Column(name = "qtde_racao_diaria")
    private BigDecimal qtdeRacaoDiaria;

    @Column(name = "unidade_peso_racao_diaria")
    private EnumUnidadePeso unidadePesoRacaoDiaria;

    @Column(name = "qtde_racao_refeicao")
    private BigDecimal qtdeRacaoRefeicao;

    @Column(name = "unidade_peso_racao_refeicao")
    private EnumUnidadePeso unidadePesoRacaoRefeicao;

    @Column(name = "frequencia_alimentacao_diaria")
    private Integer frequenciaAlimentacaoDiaria;

    @Column(name = "status_analise")
    @Convert(converter = EnumStatusAnalise.EnumConverter.class)
    private EnumStatusAnalise statusAnalise;

    @Column(name = "data_analise")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAnalise;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
