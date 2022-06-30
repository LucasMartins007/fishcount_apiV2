
package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.TituloDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(ITituloController.PATH)
public interface ITituloController {
    
    String PATH = IUsuarioController.PATH + OperationsPath.PARENT_ID + "/titulo";
    
    @PostMapping
    TituloDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody TituloDTO tituloDTO);
    
    @GetMapping
    List<TituloDTO> listar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);
    
    @PutMapping(OperationsPath.ID + "/liquidar")
    void liquidar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer tituloId);
    
    @PutMapping(OperationsPath.ID + "/cancelar")
    void calcelar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer tituloId);

}
