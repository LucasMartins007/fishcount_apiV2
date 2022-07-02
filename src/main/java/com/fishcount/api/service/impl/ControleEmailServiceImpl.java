package com.fishcount.api.service.impl;

import com.fishcount.api.service.ControleEmailService;
import com.fishcount.common.model.dto.ControleEmailDTO;
import com.fishcount.common.model.entity.ControleEmail;
import org.springframework.stereotype.Service;

@Service
public class ControleEmailServiceImpl
        extends AbstractServiceImpl<ControleEmail, Integer, ControleEmailDTO>
        implements ControleEmailService {
}
