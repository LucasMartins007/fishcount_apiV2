package com.fishcount.common.model.entity.financeiro.pix;

import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.pattern.AbstractEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_qrcode_pix_to_fish_usuario"))
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fin_qrcode_pix_to_fin_location"))
    private LocationPix locationPix;

    @Column(name = "data_inclusao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_alteracao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

}
