package com.fishcount.api.controller;

import com.fishcount.common.model.dto.AutenticacaoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = LoginController.PATH)
@Api(tags = LoginController.TAG)
@Tag(name = LoginController.TAG, description = LoginController.DESCRIPTION)
public interface LoginController {

    String PATH = "login";

    String TAG = "Login";

    String DESCRIPTION = "Endpoints referentes a autenticação da aplicação";

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.login.autenticar.operation}", notes = "${controller.login.autenticar.description}")
    ResponseEntity<AutenticacaoDTO> autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO);

}
