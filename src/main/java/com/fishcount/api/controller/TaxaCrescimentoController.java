package com.fishcount.api.controller;

import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = TaxaCrescimentoController.PATH)
@Api(tags = TaxaCrescimentoController.TAG)
@Tag(name = TaxaCrescimentoController.TAG, description = "Taxa Crescimento")
public interface TaxaCrescimentoController {
    
    String PATH = EspecieController.PATH + "/taxa";
    
    String TAG = EspecieController.TAG + " | Taxa de Crescimento";
    
    @GetMapping
    TaxaCrescimentoDTO findByEspecie(@RequestParam(value = "especie", required = true) String especie);

}
