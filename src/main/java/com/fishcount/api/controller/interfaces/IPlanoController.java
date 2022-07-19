package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPlanoController.PATH)
@Api(tags = IPlanoController.TAG)
@Tag(name = IPlanoController.TAG, description = IPlanoController.DESCRIPTION)
public interface IPlanoController {

    String PATH = "plano";

    String TAG = "Planos";

    String DESCRIPTION = "Endpoints responsáveis pela manipulação dos planos disponíveis";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.plano.listar.operation}", notes = "${controller.plano.listar.description}")
    List<PlanoDTO> listar();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "${controller.plano.incluir.operation}", notes = "${controller.plano.incluir.description}")
    PlanoDTO incluir(@RequestBody PlanoDTO planoDTO);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.plano.encontrar.operation}", notes = "${controller.plano.encontrar.description}")
    PlanoDTO encontrar(@ApiParam("${controller.plano.id}") @PathVariable(OperationsPath.ID) Integer planoId);

}
