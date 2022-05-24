
package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ITaxaCrescimentoController;
import com.fishcount.api.service.TaxaCrescimentoService;
import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaxaCrescimentoController extends AbstractController<TaxaCrescimentoService> implements ITaxaCrescimentoController {

    @Override
    public TaxaCrescimentoDTO findByEspecie(Integer idEspecie) {
        return converterEntityParaDTO(getService().findByEspecie(idEspecie), TaxaCrescimentoDTO.class);
    }

}
