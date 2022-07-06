package com.fishcount.api.controller.interfaces;

import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
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
@RequestMapping(value = ILocationPixController.PATH)
@Api(tags = ILocationPixController.TAG)
@Tag(name = ILocationPixController.TAG, description = "Autenticação")
public interface ILocationPixController {

    String PATH = IPessoaController.PATH + OperationsPath.PARENT_ID + "/pix";

    String TAG = "Pix | Location";

    @GetMapping("/qrcode" + OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    QRCodePixDTO gerarQRCode(
            @PathVariable(OperationsParam.PARENT_ID) Integer idPessoa,
            @PathVariable(OperationsParam.ID) Integer locationId
    );

}
