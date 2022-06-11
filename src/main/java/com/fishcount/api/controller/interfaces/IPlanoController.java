package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPlanoController.PATH)
@Api(tags = IPlanoController.TAG)
@Tag(name = IPlanoController.TAG, description = "Plano")
public interface IPlanoController {

    String PATH = "${api.prefix.v1}/plano";

    String TAG = "Planos";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<PlanoDTO> listarPlanos();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PlanoDTO incluir(@RequestBody PlanoDTO planoDTO);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    PlanoDTO encontrar(@PathVariable(OperationsPath.ID) Integer idPlano);

}
