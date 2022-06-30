package com.fishcount.api.controller;

import com.fishcount.api.config.security.JwtTokenUtil;
import com.fishcount.api.controller.interfaces.ILoginController;
import com.fishcount.api.service.LoginService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.classes.AutenticacaoDTO;
import com.fishcount.common.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucas
 */
@RestController
@RequiredArgsConstructor
public class LoginController extends AbstractController<LoginService> implements ILoginController {


    @Override
    public ResponseEntity<AutenticacaoDTO> authenticate(@RequestBody AutenticacaoDTO autenticacaoDTO) {
        AutenticacaoDTO autenticacao = getService().autenticar(autenticacaoDTO);

        return ResponseEntity.ok(autenticacao);
    }

}
