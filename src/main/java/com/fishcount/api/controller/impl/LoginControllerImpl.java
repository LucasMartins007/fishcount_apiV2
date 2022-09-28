package com.fishcount.api.controller.impl;

import com.fishcount.api.controller.LoginController;
import com.fishcount.api.controller.pattern.AbstractController;
import com.fishcount.api.service.LoginService;
import com.fishcount.common.model.dto.AutenticacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucas
 */
@RestController
@RequiredArgsConstructor
public class LoginControllerImpl extends AbstractController<LoginService> implements LoginController {

    private final LoginService loginService;

    @Override
    public ResponseEntity<AutenticacaoDTO> autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO) {
        AutenticacaoDTO autenticacao = loginService.autenticar(autenticacaoDTO);

        return ResponseEntity.ok(autenticacao);
    }

}
