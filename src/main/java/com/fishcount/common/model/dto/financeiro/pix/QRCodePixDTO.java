package com.fishcount.common.model.dto.financeiro.pix;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class QRCodePixDTO implements AbstractDTO<Integer> {

    private Integer id;

    private String qrCode;
    
    private LocationPixDTO locationPix;

}
