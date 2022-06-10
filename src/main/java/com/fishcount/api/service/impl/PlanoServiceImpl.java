package com.fishcount.api.service.impl;

import com.fishcount.api.service.PlanoService;
import com.fishcount.common.model.dto.PlanoDTO;
import com.fishcount.common.model.entity.Plano;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlanoServiceImpl extends AbstractServiceImpl<Plano, Integer, PlanoDTO> implements PlanoService {

    @Override
    public List<Plano> listarPlanos() {
        return getRepository().findAll();
    }

}