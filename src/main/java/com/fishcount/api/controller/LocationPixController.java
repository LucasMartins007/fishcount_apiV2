package com.fishcount.api.controller;

import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = LocationPixController.PATH)
@Api(tags = LocationPixController.TAG)
@Tag(name = LocationPixController.TAG, description = "Autenticação")
public interface LocationPixController {

    String PATH = PessoaController.PATH + OperationsPath.PARENT_ID + "/pix";

    String TAG = "Pix | Location";

    @GetMapping("/qrcode" + OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    QRCodePixDTO gerarQRCode(
            @PathVariable(OperationsParam.PARENT_ID) Integer idPessoa,
            @PathVariable(OperationsParam.ID) Integer locationId
    );

}
