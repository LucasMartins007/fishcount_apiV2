package com.fishcount.common.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.model.enums.EnumUnidadeTamanho;
import com.fishcount.common.model.pattern.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "especie")
public class Especie extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_especie", sequenceName = "gen_id_especie")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_especie")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "peso_medio")
    private BigDecimal pesoMedio;
    
    @Column(name = "unidade_peso_medio")
    @Convert(converter = EnumUnidadePeso.EnumConverter.class)
    private EnumUnidadePeso unidadePesoMedio;

    @Column(name = "tamanho_medio")
    private BigDecimal tamanhoMedio;

    @Column(name = "unidade_tamanho")
    @Convert(converter = EnumUnidadeTamanho.EnumConverter.class)
    private EnumUnidadeTamanho unidadeTamanho;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_taxa_crescimento", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_especie_to_taxa_crescimento"))
    private TaxaCrescimento taxaCrescimento;

    @Column(name = "qtde_media_racao")
    private Integer qtdeMediaRacao;
    
    @Column(name = "unidade_peso_racao")
    @Convert(converter = EnumUnidadePeso.EnumConverter.class)
    private EnumUnidadePeso unidadePesoRacao;

    @OneToMany(mappedBy = "especie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tanque> tanques;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

}
