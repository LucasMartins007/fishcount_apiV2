
package com.fishcount.api.controller;

import com.fishcount.common.model.dto.financeiro.TituloDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(TituloController.PATH)
public interface TituloController {
    
    String PATH = PessoaController.PATH + OperationsPath.PARENT_ID + "/titulo";
    
    @PostMapping
    TituloDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody TituloDTO tituloDTO);
    
    @GetMapping
    List<TituloDTO> listar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);
    
    @PutMapping(OperationsPath.ID + "/liquidar")
    void liquidar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer tituloId);
    
    @PutMapping(OperationsPath.ID + "/cancelar")
    void calcelar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer tituloId);

}
