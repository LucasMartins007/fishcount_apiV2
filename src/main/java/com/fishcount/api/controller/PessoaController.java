package com.fishcount.api.controller;

import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = PessoaController.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = PessoaController.TAG)
@Tag(name = PessoaController.TAG, description = PessoaController.DESCRIPTION)
public interface PessoaController {

    String PATH = "pessoa";

    String TAG = "Pessoa";

    String DESCRIPTION = "Endpoints referentes a manipulação da pessoa";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "${controller.pessoa.incluir.operation}", notes = "${controller.pessoa.incluir.description}")
    PessoaDTO incluir(@RequestBody PessoaDTO pessoaDTO);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pessoa.encontrar.operation}", notes = "${controller.pessoa.encontrar.description}")
    PessoaDTO encontrar(@ApiParam("${controller.pessoa.id}") @PathVariable(OperationsParam.ID) Integer id);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pessoa.atualizar.operation}", notes = "${controller.pessoa.atualizar.description}")
    void atualizar(@ApiParam("${controller.pessoa.id}") @PathVariable(OperationsParam.ID) Integer id,
                   @RequestBody PessoaDTO pessoaDTO);

}
