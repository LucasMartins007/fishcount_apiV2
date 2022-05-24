package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = ITaxaCrescimentoController.PATH)
@Api(tags = ITaxaCrescimentoController.TAG)
@Tag(name = ITaxaCrescimentoController.TAG, description = "Taxa Crescimento")
public interface ITaxaCrescimentoController {
    
    String PATH = IEspecieController.PATH + "/taxa";
    
    String TAG = IEspecieController.TAG + " | Taxa de Crescimento";
    
    @GetMapping
    TaxaCrescimentoDTO findByEspecie(@RequestParam(value = "especie", required = true) String especie);

}
