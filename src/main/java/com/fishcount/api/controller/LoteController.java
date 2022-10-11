package com.fishcount.api.controller;

import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lucas
 */
@RestController
@RequestMapping(value = LoteController.PATH)
@Api(tags = LoteController.TAG)
@Tag(name = LoteController.TAG, description = LoteController.DESCRIPTION)
public interface LoteController {

    String PATH = PessoaController.PATH + OperationsPath.PARENT_ID + "/lote";

    String TAG = "Lote";

    String DESCRIPTION = "Endpoints referentes a manipulação dos lotes da pessoa";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "${controller.lote.incluir.operation}", notes = "${controller.lote.incluir.description}")
    LoteDTO incluir(@ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                    @RequestBody LoteDTO loteDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.lote.listar.operation}", notes = "${controller.lote.listar.description}")
    List<LoteDTO> listar(@ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                         @RequestParam(name = "orderBy", required = false) String orderBy);


    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.lote.listar.operation}", notes = "${controller.lote.listar.description}")
    LoteDTO encontrarPorId(@ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                                 @ApiParam("${controller.lote.id}") @PathVariable(OperationsParam.ID) Integer loteId);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.lote.atualizar.operation}", notes = "${controller.lote.atualizar.description}")
    void atualizar(@ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                   @ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.ID) Integer loteId,
                   @RequestBody LoteDTO loteDTO);

    @DeleteMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inativar(@ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                  @ApiParam("${controller.lote.id}") @PathVariable(OperationsParam.ID) Integer loteId);

}
