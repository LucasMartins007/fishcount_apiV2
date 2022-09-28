package com.fishcount.api.controller;

import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.enums.EnumStatusPagamento;
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
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = PagamentoController.PATH)
@Api(tags = PagamentoController.TAG)
@Tag(name = PagamentoController.TAG, description = PagamentoController.DESCRIPTION)
public interface PagamentoController {

    String PATH = PessoaController.PATH + OperationsPath.PARENT_ID + "/pagamento";

    String TAG = PessoaController.TAG + " | Pagamento";

    String DESCRIPTION = "Endpoints referentes a manipulação dos pagamentos da pessoa referenciada";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "${controller.pagamento.incluir.operation}", notes = "${controller.pagamento.incluir.description}")
    PagamentoDTO incluir(@ApiParam("${controller.pagamento.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                         @RequestBody PagamentoDTO pagamentoDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pagamento.listar.operation}", notes = "${controller.pagamento.listar.description}")
    List<PagamentoDTO> listarParcelas(@ApiParam("${controller.pagamento.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pagamento.encontrar.operation}", notes = "${controller.pagamento.encontrar.description}")
    PagamentoDTO encontrar(@ApiParam("${controller.pagamento.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                           @ApiParam("${controller.pagamento.id}") @PathVariable(OperationsParam.ID) Integer pagamentoId);

    @GetMapping("/parcelas")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pagamento.listar-parcelas.operation}", notes = "${controller.pagamento.listar-parcelas.description}")
    List<PagamentoParcelaDTO> listarParcelas(@ApiParam("${controller.pagamento.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                                             @ApiParam("${controller.pagamento.queryParam}") @RequestParam(value = "status") EnumStatusPagamento statusPagamento);
}
