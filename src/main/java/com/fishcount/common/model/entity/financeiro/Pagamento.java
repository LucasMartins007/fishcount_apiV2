package com.fishcount.common.model.entity.financeiro;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "fin_pagamento")
public class Pagamento extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_pagamento", sequenceName = "gen_id_fin_pagamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_pagamento")
    private Integer id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;
    
    @Column(name = "desconto")
    private BigDecimal desconto;
    
    @Column(name = "acrescimo")
    private BigDecimal acrescimo;

    @Column(name = "status_pagamento")
    @Convert(converter = EnumStatusPagamento.EnumConverter.class)
    private EnumStatusPagamento statusPagamento;

    @Column(name = "tipo_pagamento")
    @Convert(converter = EnumTipoPagamento.EnumConverter.class)
    private EnumTipoPagamento tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_pagamento_to_fish_pessoa"))
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plano", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_pagamento_to_fin_plano"))
    private Plano plano;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_pagamento_to_fin_titulo"))
    private Titulo titulo;

    @OneToMany(mappedBy = "pagamento", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<PagamentoParcela> pagamentoParcelas;
    
    @Column(name = "qtde_parcelas")
    private Integer qtdeParcelas;

    @Column(name = "data_inicio_vigencia")
    private Date dataInicioVigencia;

    @Column(name = "data_fim_vigencia")
    private Date dataFimVigencia;

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
