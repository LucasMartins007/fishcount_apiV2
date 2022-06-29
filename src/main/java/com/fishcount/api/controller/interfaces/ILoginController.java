package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.classes.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = ILoginController.PATH)
@Api(tags = ILoginController.TAG)
@Tag(name = ILoginController.TAG, description = "Autenticação")
public interface ILoginController {

    String PATH = "${api.prefix.v1}/login";

    String TAG = "Login";

    @PostMapping
    ResponseEntity<UserDTO> authenticate(@RequestBody UserDTO authenticationRequest);

}
