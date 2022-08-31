package com.fishcount.common.model.entity;


import com.fishcount.common.model.entity.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Tabela com dados pré-cadastrados retirados da tabela
 * de arraçoamento diário do PDF de referência
 */
@Entity
@Getter
@Setter
@Table(name = "fish_configuracao_arracoamento")
public class ConfiguracaoArracoamento extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_configuracao_arracoamento", allocationSize = 1, sequenceName = "gen_id_fish_configuracao_arracoamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_configuracao_arracoamento")
    private Integer id;

    /**
     * Espécie que possui os parâmetros da tabela
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_configuracao_arracoamento_to_fish_especie"))
    private Especie especie;

    /**
     * Parametros de cálculo baseado no tipo de ração a ser dada dependendo do período
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parametro_tipo_racao", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_configuracao_arracoamento_to_fish_parametro_tipo_racao"))
    private ParametroTipoRacao parametroTipoRacao;

    /**
     * Peso mínimo dos peixes em grama
     */
    @Column(name = "peso_minimo")
    private BigDecimal pesoMinimo;

    /**
     * Peso máximo dos peixes em grama
     */
    @Column(name = "peso_maximo")
    private BigDecimal pesoMaximo;

    /**
     * Frenquência de vezes no dia que os peixes deveram ser alimentados (Ref/Dia)
     */
    @Column(name = "frequencia_dia")
    private BigInteger frequenciaDia;

    /**
     * Porcentagem do peso vivo total (biomassa) dos peixes por dia, calculado
     * por meio da multiplicação do número total de peixes pelo seu peso médio (% PV/dia)
     */
    @Column(name = "porcentagem_peso_vivo_dia")
    private BigDecimal porcentagemPesoVivoDia;

    /**
     * Não será aplicado
     * = Taxa de Conversão Alimentar esperada. Isso representa a quantidade
     * de ração consumida, em quilos, dividida pelo ganho de peso dos peixes em quilos
     */
    @Column(name = "tca_esperada")
    private BigDecimal tcaEsperada;


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

