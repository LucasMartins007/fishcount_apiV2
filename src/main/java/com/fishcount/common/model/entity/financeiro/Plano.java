package com.fishcount.common.model.entity.financeiro;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "fin_plano")
public class Plano extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_plano", allocationSize = 1, sequenceName = "gen_id_fin_plano")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_plano")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_minimo")
    private BigDecimal valorMinimo;

    @Column(name = "valor_maximo")
    private BigDecimal valorMaximo;

    @Column(name = "valor_parcela_maximo")
    private BigDecimal valorParcelaMaximo;

    @Column(name = "valor_parcela_minimo")
    private BigDecimal valorParcelaMinimo;

    @Column(name = "min_taque")
    private Integer minTanque;

    @Column(name = "max_taque")
    private Integer maxTanque;

    @Column(name = "qtde_parcela")
    private Integer qtdeParcela;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
