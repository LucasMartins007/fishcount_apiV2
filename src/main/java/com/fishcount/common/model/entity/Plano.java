package com.fishcount.common.model.entity;

import com.fishcount.common.model.pattern.AbstractEntity;
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
    private Double valorMinimo;

    @Column(name = "valor_maximo")
    private Double valorMaximo;

    @Column(name = "min_taque")
    private Integer minTanque;

    @Column(name = "miax_taque")
    private Integer maxTanque;

    @OneToMany(mappedBy = "plano", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Titulo> titulos;

}
