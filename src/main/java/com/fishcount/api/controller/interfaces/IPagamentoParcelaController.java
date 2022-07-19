package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.enums.EnumStatusPagamento;
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
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPagamentoParcelaController.PATH)
@Api(tags = IPagamentoParcelaController.TAG)
@Tag(name = IPagamentoParcelaController.TAG, description = IPagamentoParcelaController.DESCRIPTION)
public interface IPagamentoParcelaController {

    String PATH = IPagamentoController.PATH + OperationsPath.ID + "/parcela";

    String TAG = IPagamentoController.TAG + " | Parcelas";

    String DESCRIPTION = "Endpoints referentes a manipulação das parcelas do pagamento";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.pagamento-parcela.listar.operation}", notes = "${controller.pagamento-parcela.listar.description}")
    List<PagamentoParcelaDTO> listar(@ApiParam("${controller.pagamento-parcela.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                                     @ApiParam("${controller.pagamento-parcela.id}") @PathVariable(OperationsParam.ID) Integer pagamentoId,
                                     @ApiParam("${controller.pagamento-parcela.queryParam}") @RequestParam(value = "status") EnumStatusPagamento statusPagamento);

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
