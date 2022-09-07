package com.fishcount.common.model.entity;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "fish_controle_email")
public class ControleEmail extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_controle_email", sequenceName = "gen_fish_id_controle_email", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_controle_email")
    private Integer id;

    @Column(name = "email_remetente")
    private String emailRemetente;

    @Column(name = "email_destinatario")
    private String emailDestinatario;

    @Column(name = "nome_destinatario")
    private String nomeDestinatario;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "corpo_email")
    private String corpoEmail;

    @Column(name = "tipo_envio_email")
    @Convert(converter = EnumTipoEnvioEmail.EnumConverter.class)
    private EnumTipoEnvioEmail tipoEnvioEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_controle_email_to_fish_pessoa"))
    private Pessoa pessoa;

    @Column(name = "is_enviado")
    private boolean isEnviado;

    @Column(name = "excecao_envio")
    private String excecaoEnvio;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnvio;

    @PrePersist
    private void prePersist(){
        this.dataInclusao = DateUtil.getDate();
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
