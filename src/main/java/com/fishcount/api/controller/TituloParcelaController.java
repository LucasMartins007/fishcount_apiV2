package com.fishcount.api.controller;

import com.fishcount.common.model.dto.financeiro.TituloParcelaDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(TituloParcelaController.PATH)
public interface TituloParcelaController {

    String PATH = TituloController.PATH + OperationsPath.ID + "/pacela";

    @PostMapping
    TituloParcelaDTO adicionar(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.ID) Integer idTitulo,
            @RequestBody TituloParcelaDTO tituloParcelaDTO
    );

    @PutMapping(OperationsPath.CHILD_ID)
    void liquidar(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.ID) Integer idTitulo,
            @PathVariable(OperationsParam.CHILD_ID) Integer idTituloParcela
    );

    @GetMapping
    List<TituloParcelaDTO> listar(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.ID) Integer idTitulo
    );

    @GetMapping(OperationsPath.CHILD_ID)
    TituloParcelaDTO encontrar(
            @PathVariable(OperationsParam.PARENT_ID) Integer idUsuario,
            @PathVariable(OperationsParam.ID) Integer idTitulo,
            @PathVariable(OperationsParam.CHILD_ID) Integer idTituloParcela
    );
}
