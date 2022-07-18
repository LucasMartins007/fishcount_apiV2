package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.PagamentoDTO;
import com.fishcount.common.model.dto.financeiro.PagamentoParcelaDTO;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPagamentoController.PATH)
@Api(tags = IPagamentoController.TAG)
@Tag(name = IPagamentoController.TAG, description = "Pagamento")
public interface IPagamentoController {

    String PATH = IPessoaController.PATH + OperationsPath.PARENT_ID + "/pagamento";

    String TAG = "Usuário | Pagamento";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PagamentoDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody PagamentoDTO pagamentoDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<PagamentoDTO> listarPagamentos(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    PagamentoDTO consultarPagamento(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer idPagamento);

    @GetMapping("/parcelas")
    @ResponseStatus(HttpStatus.OK)
    List<PagamentoParcelaDTO> listarParcelas(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @RequestParam(value = "status", required = true) EnumStatusPagamento statusPagamento
    );
}
