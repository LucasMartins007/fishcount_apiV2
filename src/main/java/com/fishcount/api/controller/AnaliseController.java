package com.fishcount.api.controller;

import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = AnaliseController.PATH)
@Api(tags = AnaliseController.TAG)
@Tag(name = AnaliseController.TAG, description = AnaliseController.TAG)
public interface AnaliseController {

    String PATH = EspecieController.PATH + OperationsPath.PARENT_ID + "/analise";

    String TAG = "Analise";

     @PostMapping
     @ResponseStatus(HttpStatus.OK)
     @ApiOperation(value = "${controller.email.incluir.operation}", notes = "${controller.email.incluir.description}")
     BigDecimal teste(@RequestBody TanqueDTO tanqueDTO, @PathVariable(OperationsParam.PARENT_ID) Integer especieId);


}
