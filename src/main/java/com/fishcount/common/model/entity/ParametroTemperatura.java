package com.fishcount.common.model.entity;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "fish_parametro_temperatura")
public class ParametroTemperatura extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_parametro_temperatura", allocationSize = 1, sequenceName = "gen_id_fish_parametro_temperatura")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_parametro_temperatura")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_parametro_temperatura_to_fish_especie"))
    private Especie especie;

    @Column(name = "temperatura_minima")
    private Integer temperaturaMinima;

    @Column(name = "temperatura_maxima")
    private Integer temperaturaMaxima;

    @Column(name = "porcentagem_desconto_racao")
    private BigDecimal porcentagemDescontoRacao;

    @Column(name = "temperatura_valida")
    private boolean isTemperaturaValida;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
