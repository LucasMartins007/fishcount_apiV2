package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
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
@RequestMapping(value = ILoteController.PATH)
@Api(tags = ILoteController.TAG)
@Tag(name = ILoteController.TAG, description = ILoteController.DESCRIPTION)
public interface ILoteController {

    String PATH = IPessoaController.PATH + OperationsPath.PARENT_ID + "/lote";

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
    List<LoteDTO> listar(@ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId);


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.lote.atualizar.operation}", notes = "${controller.lote.atualizar.description}")
    void atualizar(@ApiParam("${controller.lote.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                   @RequestBody LoteDTO loteDTO);

}
