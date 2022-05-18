package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.TanqueDTO;
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
@RequestMapping(value = ITanqueController.PATH)
@Api(tags = ITanqueController.TAG)
@Tag(name = ITanqueController.TAG, description = "Tanque")
public interface ITanqueController {
    
    String PATH = ILoteController.PATH + OperationsPath.CHILD_ID + "/tanque";
    
    String TAG = ILoteController.TAG + " | Tanque";
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TanqueDTO incluir(@PathVariable(OperationsParam.CHILD_ID) Integer loteId, @RequestBody TanqueDTO tanqueDTO);
        
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TanqueDTO> listarTanquesFromLote(@PathVariable(OperationsParam.CHILD_ID) Integer loteId);

}