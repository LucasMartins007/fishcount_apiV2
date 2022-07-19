package com.fishcount.api.controller;

import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.pattern.constants.OperationsParam;
import com.fishcount.common.model.pattern.constants.OperationsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lucas
 */
@RestController
@RequestMapping(value = EmailController.PATH)
@Api(tags = EmailController.TAG)
@Tag(name = EmailController.TAG, description = EmailController.DESCRIPTION)
public interface EmailController {

    String PATH = PessoaController.PATH + OperationsPath.PARENT_ID + "/email";

    String TAG = "Email";

    String DESCRIPTION = "Endpoints responsáveis pela manipulação de emails";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "${controller.email.incluir.operation}", notes = "${controller.email.incluir.description}")
    EmailDTO incluir(@ApiParam("${controller.email.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                     @RequestBody EmailDTO emailDTO);

    @PutMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.email.editar.operation}", notes = "${controller.email.editar.description}")
    void editar(@ApiParam("${controller.email.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId, @PathVariable(OperationsParam.ID) Integer idEmail,
                @RequestBody EmailDTO emailDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.email.listar.operation}", notes = "${controller.email.listar.description}")
    List<EmailDTO> listar(@ApiParam("${controller.email.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId);

    @GetMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "${controller.email.encontrar.operation}", notes = "${controller.email.encontrar.description}")
    EmailDTO encontrar(@ApiParam("${controller.email.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                       @PathVariable(OperationsParam.ID) Integer idEmail);

    @DeleteMapping(OperationsPath.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "${controller.email.inativar.operation}", notes = "${controller.email.inativar.description}")
    void inativar(@ApiParam("${controller.email.parentId}") @PathVariable(OperationsParam.PARENT_ID) Integer pessoaId,
                  @ApiParam("${controller.email.id}") @PathVariable(OperationsParam.ID) Integer emailId);
}
