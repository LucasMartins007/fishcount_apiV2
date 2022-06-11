package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IPlanoController;
import com.fishcount.api.service.PlanoService;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanoController extends AbstractController<PlanoService> implements IPlanoController {

    @Override
    public List<PlanoDTO> listarPlanos() {
        return converterEntityParaDTO(getService().listarPlanos(), PlanoDTO.class);
    }

}
