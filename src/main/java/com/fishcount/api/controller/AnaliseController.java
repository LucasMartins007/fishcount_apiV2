package com.fishcount.api.controller;

import com.fishcount.common.model.dto.AnaliseDTO;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "${controller.analise.requisitar.operation}", notes = "${controller.analise.requisitar.description}")
    AnaliseDTO requisitarInicioAnalise(@ApiParam("${controller.analise.tanqueId}") @RequestParam(name = "tanqueId", required = true) Integer tanqueId,
                                       @ApiParam("${controller.analise.temperatura}") @RequestParam(name = "temperatura", required = false) Integer temperatura);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.analise.simular.operation}", notes = "${controller.analise.simular.description}")
    AnaliseDTO simularAnaliseConcluida(@ApiParam("${controller.analise.id}") @PathVariable(OperationsParam.ID) Integer analiseId,
                                       @ApiParam("${controller.analise.tanqueId}") @RequestParam(name = "tanqueId", required = true) Integer tanqueId,
                                       @ApiParam("${controller.analise.temperatura}") @RequestParam(name = "temperatura", required = false) Integer temperatura);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AnaliseDTO> listarPorTanque(@RequestParam(name = "tanqueId", required = true) Integer tanqueId,
                                     @RequestParam(name = "statusAnalise", required = false) EnumStatusAnalise statusAnalise);


}
