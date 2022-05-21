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
@RequestMapping(value = ITelefoneController.PATH)
public interface ITelefoneController {

    String PATH = IUsuarioController.PATH + OperationsPath.PARENT_ID + "/telefone";

    @PostMapping
    TelefoneDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody TelefoneDTO telefoneDTO);

    @PutMapping(OperationsParam.ID)
    void editar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsPath.ID) Integer idTelefone, @RequestBody TelefoneDTO telefoneDTO);

    @GetMapping
    List<TelefoneDTO> listar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);

    @GetMapping(OperationsParam.ID)
    TelefoneDTO encontrar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsPath.ID) Integer idTelefone);

}
