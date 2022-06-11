package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPagamentoParcelaController.PATH)
@Api(tags = IPagamentoParcelaController.TAG)
@Tag(name = IPagamentoParcelaController.TAG, description = "Parcelas")
public interface IPagamentoParcelaController {

    String PATH = IPagamentoController.PATH + OperationsPath.ID + "/parcela";

    String TAG = "Pagamento | Parcelas";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<PagamentoParcelaDTO> listarParcelas(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.ID) Integer idPagamento,
            @RequestParam(value = "status", required = true) EnumStatusPagamento statusPagamento
    );

    @GetMapping(OperationsParam.CHILD_ID)
    @ResponseStatus(HttpStatus.OK)
    PagamentoParcelaDTO encontrarParcela(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.ID) Integer idPagamento,
            @PathVariable(OperationsParam.CHILD_ID) Integer idParcela
    );

    @PutMapping(OperationsParam.CHILD_ID)
    @ResponseStatus(HttpStatus.OK)
    QRCodePixDTO gerarQRCodeByParcela(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.CHILD_ID) Integer idParcela
    );

}
