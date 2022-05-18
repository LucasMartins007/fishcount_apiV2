package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(ITelefoneController.PATH)
public interface ITelefoneController {

    String PATH = IUsuarioController.PATH + OperationsParam.PARENT_ID + "/telefone";

    @PostMapping
    TelefoneDTO incluir(@PathVariable(OperationsPath.PARENT_ID) Integer idUsuario, @RequestBody TelefoneDTO telefoneDTO);

    @PutMapping(OperationsParam.ID)
    void editar(@PathVariable(OperationsPath.PARENT_ID) Integer idUsuario, @PathVariable(OperationsPath.ID) Integer idTelefone, @RequestBody TelefoneDTO telefoneDTO);

    @GetMapping
    List<TelefoneDTO> listar(@PathVariable(OperationsPath.PARENT_ID) Integer idUsuario);

    @GetMapping(OperationsParam.ID)
    TelefoneDTO encontrar(@PathVariable(OperationsPath.PARENT_ID) Integer idUsuario, @PathVariable(OperationsPath.ID) Integer idTelefone);

}
