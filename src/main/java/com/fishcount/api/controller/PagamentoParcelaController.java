package com.fishcount.api.controller;

import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = PagamentoParcelaController.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = PagamentoParcelaController.TAG)
@Tag(name = PagamentoParcelaController.TAG, description = PagamentoParcelaController.DESCRIPTION)
public interface PagamentoParcelaController {

    String PATH = PagamentoController.PATH + OperationsPath.ID + "/parcela";

    String TAG = PagamentoController.TAG + " | Parcelas";

    String DESCRIPTION = "Endpoints referentes a manipulação das parcelas do pagamento";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pagamento-parcela.listar.operation}", notes = "${controller.pagamento-parcela.listar.description}")
    List<PagamentoParcelaDTO> listar(@ApiParam("${controller.pagamento-parcela.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                                     @ApiParam("${controller.pagamento-parcela.id}") @PathVariable(OperationsParam.ID) Integer pagamentoId,
                                     @ApiParam("${controller.pagamento-parcela.queryParam}") @RequestParam(value = "status", required = false) EnumStatusPagamento statusPagamento);

    @GetMapping(OperationsPath.CHILD_ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pagamento-parcela.encontrar.operation}", notes = "${controller.pagamento-parcela.encontrar.description}")
    PagamentoParcelaDTO encontrar(@ApiParam("${controller.pagamento.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                                  @ApiParam("${controller.pagamento-parcela.parentId}") @PathVariable(OperationsParam.ID) Integer pagamentoId,
                                  @ApiParam("${controller.pagamento-parcela.id}") @PathVariable(OperationsParam.CHILD_ID) Integer parcelaId);

    @PutMapping(OperationsPath.CHILD_ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pagamento-parcela.gerarQRCode.operation}", notes = "${controller.pagamento-parcela.gerarQRCode.description}")
    QRCodePixDTO gerarQRCode(@ApiParam("${controller.pagamento.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                             @ApiParam("${controller.pagamento-parcela.parentId}") @PathVariable(OperationsParam.ID) Integer pagamentoId,
                             @ApiParam("${controller.pagamento-parcela.id}") @PathVariable(OperationsParam.CHILD_ID) Integer parcelaId);

}
