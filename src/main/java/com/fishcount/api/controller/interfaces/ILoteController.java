package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.LoteDTO;
import com.fishcount.common.model.pattern.OperationsParam;
import com.fishcount.common.model.pattern.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = ILoteController.PATH)
@Api(tags = ILoteController.TAG)
@Tag(name = ILoteController.TAG, description = "Lote")
public interface ILoteController {

    String PATH = IPessoaController.PATH + OperationsPath.PARENT_ID + "/lote";

    String TAG = "Lote";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LoteDTO incluir(@PathVariable(OperationsParam.PARENT_ID) Integer idPessoa, @RequestBody LoteDTO loteDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<LoteDTO> listarLotesFromUsuario(@PathVariable(OperationsParam.PARENT_ID) Integer idPessoa);
    
    
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void atualizar(@PathVariable(OperationsParam.PARENT_ID) Integer idPessoa, @RequestBody LoteDTO loteDTO);

}
