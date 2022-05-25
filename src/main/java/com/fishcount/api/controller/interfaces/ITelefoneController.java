package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = ITelefoneController.PATH)
public interface ITelefoneController {

    String PATH = IUsuarioController.PATH + OperationsPath.PARENT_ID + "/telefone";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TelefoneDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody TelefoneDTO telefoneDTO);

    @PutMapping(OperationsParam.ID)
    @ResponseStatus(HttpStatus.OK)
    void editar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsPath.ID) Integer idTelefone, @RequestBody TelefoneDTO telefoneDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TelefoneDTO> listar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);

    @GetMapping(OperationsParam.ID)
    @ResponseStatus(HttpStatus.OK)
    TelefoneDTO encontrar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsPath.ID) Integer idTelefone);
    
    @DeleteMapping(OperationsParam.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inativar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsPath.ID) Integer idTelefone);

}
