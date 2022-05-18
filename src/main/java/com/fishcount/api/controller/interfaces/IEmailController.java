package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = IEmailController.PATH)
public interface IEmailController {

    String PATH = IUsuarioController.PATH + OperationsPath.PARENT_ID + "/email";

    @PostMapping
    EmailDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @RequestBody EmailDTO emailDTO);

    @PutMapping(OperationsPath.ID)
    void editar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer idEmail, @RequestBody EmailDTO emailDTO);

    @GetMapping
    List<EmailDTO> listar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario);

    @GetMapping(OperationsPath.ID)
    EmailDTO encontrar(@PathVariable(OperationsParam.PARENT_ID) Integer idUsuario, @PathVariable(OperationsParam.ID) Integer idEmail);
}
