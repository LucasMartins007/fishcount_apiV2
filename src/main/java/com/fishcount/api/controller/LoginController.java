package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.ILoginController;
import com.fishcount.api.service.LoginService;
import com.fishcount.common.model.dto.AutenticacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucas
 */
@RestController
public class LoginController extends AbstractController<LoginService> implements ILoginController {


    @Override
    public ResponseEntity<AutenticacaoDTO> autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO) {
        AutenticacaoDTO autenticacao = getService().autenticar(autenticacaoDTO);

        return ResponseEntity.ok(autenticacao);
    }

}
