package com.fishcount.api.controller;

import com.fishcount.common.model.dto.financeiro.TituloParcelaDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lucas
 */
@RestController
@RequestMapping(TituloParcelaController.PATH)
public interface TituloParcelaController {

    String PATH = TituloController.PATH + OperationsPath.ID + "/pacela";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TituloParcelaDTO adicionar(@PathVariable(OperationsParam.PARENT_ID) Integer usuarioId,
                               @PathVariable(OperationsParam.ID) Integer tituloId,
                               @RequestBody TituloParcelaDTO tituloParcelaDTO);

    @PutMapping(OperationsPath.CHILD_ID)
    @ResponseStatus(HttpStatus.OK)
    void liquidar(@PathVariable(OperationsParam.PARENT_ID) Integer usuarioId,
                  @PathVariable(OperationsParam.ID) Integer tituloId,
                  @PathVariable(OperationsParam.CHILD_ID) Integer idTituloParcela);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TituloParcelaDTO> listar(@PathVariable(OperationsParam.PARENT_ID) Integer usuarioId,
                                  @PathVariable(OperationsParam.ID) Integer tituloId);

    @GetMapping(OperationsPath.CHILD_ID)
    @ResponseStatus(HttpStatus.OK)
    TituloParcelaDTO encontrar(@PathVariable(OperationsParam.PARENT_ID) Integer usuarioId,
                               @PathVariable(OperationsParam.ID) Integer tituloId,
                               @PathVariable(OperationsParam.CHILD_ID) Integer tituloParcelaId);
}
