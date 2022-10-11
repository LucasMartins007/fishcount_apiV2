package com.fishcount.api.controller;

import com.fishcount.api.controller.generic.AbstractMockController;
import com.fishcount.api.controller.impl.LocationPixControllerImpl;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.common.model.dto.financeiro.PlanoDTO;
import com.fishcount.common.model.dto.financeiro.pix.QRCodePixDTO;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class LocationPixControllerImplTest extends AbstractMockController {

    @InjectMocks
    private LocationPixControllerImpl locationPixController;

    @Mock
    private LocationPixService locationPixService;

    private QRCodePixDTO qrCodePixDTO;

    private QRCodePix qrCodePix;

    private static final String url = "/" + LocationPixController.PATH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        qrCodePixDTO = new QRCodePixDTO();
        qrCodePix = new QRCodePix();
        mockMvc = MockMvcBuilders
                .standaloneSetup(locationPixController)
                .build();
    }

    @Test
    void testarEndpoint_EncontrarQRCodePorLocation() throws Exception {
        when(locationPixService.gerarQrCodePorLocation(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(qrCodePix);

        mockMvc.perform(get(url + "/location/{locationId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testarEndpoint_EncontrarQRCodePorParcela() throws Exception {
        when(locationPixService.gerarQrCodePorPagamentoParcela(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(qrCodePix);

        when(mapper.map(qrCodePix, QRCodePixDTO.class))
                .thenReturn(qrCodePixDTO);

        MvcResult result = mockMvc.perform(get(url + "/parcela/{locationId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(asJsonString(qrCodePixDTO), result.getResponse().getContentAsString());
    }

}
