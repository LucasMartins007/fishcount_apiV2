package com.fishcount.common.model.entity;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "fish_parametro_tipo_racao")
public class ParametroTipoRacao extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_arracoamento_diario", allocationSize = 1, sequenceName = "gen_id_fish_arracoamento_diario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_arracoamento_diario")
    private Integer id;

    @OneToMany(mappedBy = "parametroTipoRacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConfiguracaoArracoamento> configuracoesArracoamentos;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "proteina_bruta_maxima")
    private BigDecimal proteinaBrutaMaxima;

    @Column(name = "proteina_bruta_minima")
    private BigDecimal proteinaBrutaMinima;

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
