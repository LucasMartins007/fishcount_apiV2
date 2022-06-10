package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.UsuarioDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = IUsuarioController.PATH)
@Api(tags = IUsuarioController.TAG)
@Tag(name = IUsuarioController.TAG, description = "Autenticação")
public interface IUsuarioController {
   
    String PATH = "${api.prefix.v1}/usuario";
    
    String TAG = "Usuário";
    
    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    UsuarioDTO incluir(@RequestBody UsuarioDTO usuarioDTO);
    
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UsuarioDTO findById(@PathVariable("id") Integer id);
    
}
