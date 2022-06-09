package com.fishcount.api.controller;

import com.fishcount.api.controller.interfaces.IPagamentoController;
import com.fishcount.api.service.PagamentoService;
import com.fishcount.common.model.dto.PagamentoDTO;
import com.fishcount.common.model.entity.Pagamento;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController extends AbstractController<PagamentoService> implements IPagamentoController {

    @Override
    public PagamentoDTO incluir(Integer idUsuario, PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = getService().incluir(converterDTOParaEntity(pagamentoDTO, Pagamento.class), idUsuario);

        return converterEntityParaDTO(pagamento, PagamentoDTO.class);
    }

    @Override
    public void editar(Integer idUsuario, Integer idPagamento, PagamentoDTO pagamentoDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PagamentoDTO> listarPagamentos(Integer idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PagamentoDTO consultarPagamento(Integer idUsuario, Integer idPagamento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
