package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPlanoController.PATH)
@Api(tags = IPlanoController.TAG)
@Tag(name = IPlanoController.TAG, description = "Plano")
public interface IPlanoController {

    String PATH = "plano";

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
