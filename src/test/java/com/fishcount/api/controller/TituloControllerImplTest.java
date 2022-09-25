package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.TituloControllerImpl;
import com.fishcount.api.service.TituloService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TituloControllerImplTest extends AbstractMockController {

    @InjectMocks
    private TituloControllerImpl tituloController;

    @Mock
    private TituloService tituloService;


}
