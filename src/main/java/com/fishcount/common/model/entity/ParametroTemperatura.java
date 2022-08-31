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
@Table(name = "fish_parametros_temperatura")
public class ParametroTemperatura extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_especie_parametros_temperatura", allocationSize = 1, sequenceName = "gen_id_fish_especie_parametros_temperatura")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_especie_parametros_temperatura")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_arracoamento_diario",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_fish_parametros_temperatura_to_fish_configuracao_arracoamento"))
    private ConfiguracaoArracoamento arracoamentoDiario;

    @Column(name = "temperatura_minima")
    private Integer temperaturaMinima;

    @Column(name = "temperatura_maxima")
    private Integer temperaturaMaxima;

    @Column(name = "porcentagem_desconto_racao")
    private BigDecimal porcentagemDescontoRacao;

    @Column(name = "temperatura_valida")
    private boolean isTemperaturaValida;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @PreUpdate
    private void preUpdate(){
        this.dataAtualizacao = DateUtil.getDate();
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
