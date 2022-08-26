package com.fishcount.common.model.entity;


import com.fishcount.common.model.enums.EnumTipoRacao;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.model.enums.EnumUnidadeTamanho;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jhonatan
 */
@Entity
@Getter
@Setter
@Table(name = "fish_arracoamento_diario")
public class ArracoamentoDiario extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_arracoamento_diario", allocationSize = 1, sequenceName = "gen_id_fish_arracoamento_diario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_arracoamento_diario")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "peso_medio")
    private BigDecimal pesoMedio;

    @Column(name = "tipo_de_racao")
    @Convert(converter = EnumTipoRacao.EnumConverter.class)
    private String tipoDeRacao;

    @Column(name = "quantidade_dia")
    private BigInteger quantidadeDia;
    //%PV/dia
    @Column(name = "peso_vivo_dia")
    @Convert(converter = EnumUnidadeTamanho.EnumConverter.class)
    private BigDecimal pesoVivoDia;

    @Column(name = "TCA_esperada")
    @Convert(converter = EnumUnidadeTamanho.EnumConverter.class)
    private BigDecimal TCAEsperada;

    /*@Column (name = "temperatura")
    @Convert(converter = EnumTemperatura.EnumConverter.class)
    private BigDecimal  temperatura;
    */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

