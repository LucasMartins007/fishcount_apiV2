
package com.fishcount.common.model.entity.financeiro.pix;

import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Lucas Martins
 */
@Entity
@Setter
@Getter
@Table(name = "fin_location_pix")
public class LocationPix extends AbstractEntity<Integer> {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fin_location_pix", sequenceName = "gen_id_fin_location_pix")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fin_location_pix")
    private Integer id;
    
    @Column(name = "id_location")
    private Integer idLocation;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "tipo_cobranca")
    private String tipoCob;
    
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
