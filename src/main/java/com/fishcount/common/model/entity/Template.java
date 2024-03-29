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
@Table(name = "fish_template")
public class Template extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_template", sequenceName = "gen_id_fish_template", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_template")
    private Integer id;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "tipo_envio_email")
    @Convert(converter = EnumTipoEnvioEmail.EnumConverter.class)
    private EnumTipoEnvioEmail tipoEnvioEmail;

    @Column(name = "corpo_html")
    private String corpoHtml;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
