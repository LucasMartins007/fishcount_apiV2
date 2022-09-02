package com.fishcount.api.controller;

import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = AnaliseController.PATH)
@Api(tags = AnaliseController.TAG)
@Tag(name = AnaliseController.TAG, description = AnaliseController.TAG)
public interface AnaliseController {

    String PATH = "analise";

    String TAG = "Analise";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AnaliseDTO requisitarInicioAnalise(@RequestParam(name = "tanqueId", required = true) Integer tanqueId,
                                       @RequestParam(name = "temperatura", required = false) Integer temperatura);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AnaliseDTO> listarPorTanque(@RequestParam(name = "tanqueId", required = true) Integer tanqueId);


}
