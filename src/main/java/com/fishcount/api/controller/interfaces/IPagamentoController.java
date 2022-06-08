package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.PagamentoDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPagamentoController.PATH)
@Api(tags = IPagamentoController.TAG)
@Tag(name = IPagamentoController.TAG, description = "Pagamento")
public interface IPagamentoController {

    String PATH = IUsuarioController.PATH + OperationsPath.PARENT_ID + "/pagamento";

    String TAG = "Usuário | Pagamento";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PagamentoDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody PagamentoDTO pagamentoDTO);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    void editar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.ID) Integer idPagamento,
            @RequestBody PagamentoDTO pagamentoDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<PagamentoDTO> listarPagamentos(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    PagamentoDTO consultarPagamento(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer idPagamento);
}
