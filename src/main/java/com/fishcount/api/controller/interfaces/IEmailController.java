package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.EmailDTO;
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
@RequestMapping(value = IEmailController.PATH)
public interface IEmailController {

    String PATH = IUsuarioController.PATH + OperationsPath.PARENT_ID + "/email";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EmailDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody EmailDTO emailDTO);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    void editar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer idEmail, @RequestBody EmailDTO emailDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<EmailDTO> listar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    EmailDTO encontrar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer idEmail);
    
    @DeleteMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inativar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer idEmail);
}
