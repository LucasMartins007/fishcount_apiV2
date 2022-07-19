package com.fishcount.api.controller;

import com.fishcount.common.model.dto.TanqueDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
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
@RequestMapping(value = TanqueController.PATH)
@Api(tags = TanqueController.TAG)
@Tag(name = TanqueController.TAG, description = "Tanque")
public interface TanqueController {
    
    String PATH = LoteController.PATH + OperationsPath.CHILD_ID + "/tanque";
    
    String TAG = LoteController.TAG + " | Tanque";
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TanqueDTO incluir(@PathVariable(OperationsParam.CHILD_ID) Integer loteId, @RequestBody TanqueDTO tanqueDTO);
        
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TanqueDTO> listarTanquesFromLote(@PathVariable(OperationsParam.CHILD_ID) Integer loteId);

}