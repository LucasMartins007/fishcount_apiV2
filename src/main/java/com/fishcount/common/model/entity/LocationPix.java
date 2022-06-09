
package com.fishcount.common.model.entity;

import com.fishcount.common.model.pattern.AbstractEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Setter
@Getter
@Table(name = "fish_location_pix")
public class LocationPix extends AbstractEntity<Integer> {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_location_pix", sequenceName = "gen_id_fish_location_pix")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_location_pix")
    private Integer id;
    
    @Column(name = "idLocation")
    private Integer idLocation;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "tipo_cobranca")
    private String tipoCob;
    
    @Column(name = "cricao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date criacao;

}
