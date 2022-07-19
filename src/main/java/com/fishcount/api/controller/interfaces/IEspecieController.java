package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.EspecieDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IEspecieController.PATH)
@Api(tags = IEspecieController.TAG)
@Tag(name = IEspecieController.TAG, description = IEspecieController.DESCRIPTION)
public interface IEspecieController {

    String PATH = "especie";

    String TAG = "Especies";

    String DESCRIPTION = "Endpoints responsáveis pela manipulação das espécies pré cadastradas";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.especie.listar.operation}", notes = "${controller.especie.listar.description}")
    List<EspecieDTO> listar();

    @GetMapping("/find")
    @ApiOperation(value = "${controller.especie.encontrar.operation}", notes = "${controller.especie.encontrar.description}")
    EspecieDTO encontrarPorDescricao(@RequestParam(value = "descricao", required = true) String descricao);

}
