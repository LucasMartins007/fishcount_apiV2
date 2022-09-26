package com.fishcount.api.service;

import com.fishcount.api.service.pattern.IAbstractService;
import com.fishcount.common.model.dto.TelefoneDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;

import java.util.List;

/**
 *
 * @author lucas
 */
public interface TelefoneService extends IAbstractService<Telefone, Integer, TelefoneDTO>{
    
   Telefone incluir(Integer idUsuario, Telefone telefone);
   
   void editar(Integer idUsuario, Integer idTelefone, Telefone telefone);
   
   List<Telefone> listarAtivos(Integer idUsuario);
   
   void inativar(Integer idUsuario, Integer idTelefone);

   void onPrepareUpdate(Integer idTelefone, Telefone telefone);

    void validarTelefones(Pessoa pessoa);
}
