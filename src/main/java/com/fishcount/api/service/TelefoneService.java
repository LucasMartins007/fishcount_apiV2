package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Telefone;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface TelefoneService extends IAbstractService<Telefone, Integer, TelefoneDTO>{
    
   Telefone incluir(Integer idUsuario, Telefone telefone);
   
   void editar(Integer idUsuario, Telefone telefone);
   
   List<Telefone> listar(Integer idUsuario);
   
   void inativar(Integer idUsuario, Telefone telefone);
}
