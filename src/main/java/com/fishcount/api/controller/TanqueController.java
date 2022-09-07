package com.fishcount.api.controller;

import com.fishcount.common.model.dto.TanqueDTO;
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
@RequestMapping(value = TanqueController.PATH)
@Api(tags = TanqueController.TAG)
@Tag(name = TanqueController.TAG, description = "Tanque")
public interface TanqueController {

    String PATH = LoteController.PATH + OperationsPath.CHILD_ID + "/tanque";

    String TAG = LoteController.TAG + " | Tanque";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "${controller.tanque.incluir.operation}", notes = "${controller.tanque.incluir.description}")
    TanqueDTO incluir(@PathVariable(OperationsParam.CHILD_ID) Integer loteId, @RequestBody TanqueDTO tanqueDTO);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.tanque.incluir.operation}", notes = "${controller.tanque.incluir.description}")
    void editar(@ApiParam("${controller.tanque.pessoaId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                @ApiParam("${controller.tanque.loteId}") @PathVariable(OperationsParam.CHILD_ID) Integer loteId,
                @ApiParam("${controller.tanque.id}") @PathVariable(OperationsParam.ID) Integer tanqueId,
                @RequestBody TanqueDTO tanqueDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.tanque.listar.operation}", notes = "${controller.tanque.listar.description}")
    List<TanqueDTO> listarTanquesFromLote(@ApiParam("${controller.tanque.pessoaId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                                          @ApiParam("${controller.tanque.loteId}") @PathVariable(OperationsParam.CHILD_ID) Integer loteId,
                                          @ApiParam("${controller.tanque.orderBy}") @RequestParam(name = "orderBy", required = false) String orderBy);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.tanque.encontrar.operation}", notes = "${controller.tanque.encontrar.description}")
    TanqueDTO encontrarPorId(@ApiParam("${controller.tanque.pessoaId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                             @ApiParam("${controller.tanque.loteId}") @PathVariable(OperationsParam.CHILD_ID) Integer loteId,
                             @ApiParam("${controller.tanque.id}") @PathVariable(OperationsParam.ID) Integer tanqueId);

    @DeleteMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "${controller.tanque.inativar.operation}", notes = "${controller.tanque.inativar.description}")
    void inativarTanque(@ApiParam("${controller.tanque.pessoaId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                        @ApiParam("${controller.tanque.loteId}") @PathVariable(OperationsParam.CHILD_ID) Integer loteId,
                        @ApiParam("${controller.tanque.id}") @PathVariable(OperationsParam.ID) Integer tanqueId);

}