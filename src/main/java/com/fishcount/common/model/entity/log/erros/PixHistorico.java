package com.fishcount.common.model.entity.log.erros;

import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "err_pix_historico")
public class PixHistorico extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_pix_historico", sequenceName = "gen_id_pix_historico")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pix_historico")
    private Integer id;

    @Column(name = "assinatura_metodo")
    private String assinaturaMetodo;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "valor")
    private BigDecimal valor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_err_pix_historico_to_fish_usuario"))
    private Usuario usuario;

}
