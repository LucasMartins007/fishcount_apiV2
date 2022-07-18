package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.UsuarioDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = IUsuarioController.PATH)
@Api(tags = IUsuarioController.TAG)
@Tag(name = IUsuarioController.TAG, description = "teste")
public interface IUsuarioController {
   
    String PATH = "usuario";
    
    String TAG = "Usuário";
    
    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    UsuarioDTO incluir(@RequestBody UsuarioDTO usuarioDTO);
    
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UsuarioDTO findById(@PathVariable("id") Integer id);
    
}
