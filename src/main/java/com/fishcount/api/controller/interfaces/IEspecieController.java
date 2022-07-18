package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.EspecieDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IEspecieController.PATH)
@Api(tags = IEspecieController.TAG)
@Tag(name = IEspecieController.TAG, description = "Autenticação")
public interface IEspecieController {

    String PATH = "especie";

    String TAG = "Especies";

    @GetMapping
    List<EspecieDTO> listarEspecies();

    @GetMapping("/find")
    EspecieDTO findByDescricao(@RequestParam(value = "descricao", required = true) String descricao);

}
