package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.TituloParcelaDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(ITituloParcelaController.PATH)
public interface ITituloParcelaController {

    String PATH = ITituloController.PATH + OperationsPath.ID + "/pacela";

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
