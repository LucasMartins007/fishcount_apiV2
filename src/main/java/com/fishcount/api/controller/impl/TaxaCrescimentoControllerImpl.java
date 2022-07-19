
package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.TaxaCrescimentoService;
import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaxaCrescimentoControllerImpl extends AbstractController<TaxaCrescimentoService> implements com.fishcount.api.controller.TaxaCrescimentoController {

    @Override
    public TaxaCrescimentoDTO findByEspecie(String especie) {
        return converterEntityParaDTO(getService().findByEspecie(especie), TaxaCrescimentoDTO.class);
    }

}
