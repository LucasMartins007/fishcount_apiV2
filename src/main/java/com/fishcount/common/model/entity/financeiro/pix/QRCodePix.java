package com.fishcount.common.model.entity.financeiro.pix;

import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Lucas Martins
 */
@Entity
@Getter
@Setter
@Table(name = "fin_qrcode_pix")
public class QRCodePix extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_pix_qr_code", sequenceName = "gen_id_fin_pix_qr_code")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_pix_qr_code")
    private Integer id;

    @Column(name = "qr_code")
    private String qrCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_qrcode_pix_to_fish_pessoa"))
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_qrcode_pix_to_fin_location"))
    private LocationPix locationPix;

    @Column(name = "data_inclusao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_alteracao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
