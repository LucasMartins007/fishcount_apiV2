package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IPessoaController.PATH)
@Api(tags = IPessoaController.TAG)
@Tag(name = IPessoaController.TAG, description = "Pessoa")
public interface IPessoaController {

    String PATH = "${api.prefix.v1}/pessoa";

    String TAG = "Pessoa";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PessoaDTO incluir(@RequestBody PessoaDTO pessoaDTO);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    PessoaDTO encontrarPorId(@PathVariable(OperationsParam.ID) Integer id);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    void atualizar(@PathVariable(OperationsParam.ID) Integer id, @RequestBody PessoaDTO pessoaDTO);

}
