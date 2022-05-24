package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = IEspecieController.PATH)
@Api(tags = IEspecieController.TAG)
@Tag(name = IEspecieController.TAG, description = "Autenticação")
public interface IEspecieController {

    String PATH = "${api.prefix.v1}/especie";

    String TAG = "Especies";

    @GetMapping
    List<EspecieDTO> listarEspecies();

    @GetMapping("/find")
    EspecieDTO findByDescricao(@RequestParam(value = "descricao", required = true) String descricao);

}
