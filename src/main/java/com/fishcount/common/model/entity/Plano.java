package com.fishcount.common.model.entity;

import com.fishcount.common.model.pattern.AbstractEntity;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "fish_plano")
public class Plano extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_plano", sequenceName = "gen_fish_id_plano")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_plano")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_minimo")
    private BigDecimal valorMinimo;

    @Column(name = "valor_maximo")
    private BigDecimal valorMaximo;

    @Column(name = "min_taque")
    private Integer minTanque;

    @Column(name = "max_taque")
    private Integer maxTanque;
    
    @Column(name = "num_parcelas")
    private Integer numParcelas;
    
    @Column(name = "ativo")
    private boolean ativo;

}
