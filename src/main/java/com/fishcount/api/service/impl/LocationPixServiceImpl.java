package com.fishcount.api.service.impl;

import com.fishcount.api.repository.LocationPixRepository;
import com.fishcount.api.repository.QRCodePixRepository;
import com.fishcount.api.service.CobrancaPixService;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.api.service.PagamentoParcelaService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.service.gerencianet.pix.location.ClientLocationPix;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.common.model.classes.gerencianet.response.PayloadLocationResponse;
import com.fishcount.common.model.classes.gerencianet.response.PayloadQRCodeResponse;
import com.fishcount.common.model.dto.financeiro.pix.LocationPixDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.PagamentoParcela;
import com.fishcount.common.model.entity.financeiro.pix.CobrancaPix;
import com.fishcount.common.model.entity.financeiro.pix.LocationPix;
import com.fishcount.common.model.entity.financeiro.pix.QRCodePix;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationPixServiceImpl extends AbstractServiceImpl<LocationPix, Integer, LocationPixDTO> implements LocationPixService {

    private final ClientLocationPix clientLocationPix;

    @Override
    public LocationPix incluir(PayloadLocationResponse payload) {
        final LocationPix location = new LocationPix();

        location.setDataCriacao(payload.getCriacao());
        location.setLocation(payload.getLocation());
        location.setTipoCob(payload.getTipoCob());
        location.setIdLocation(payload.getId());

        return getRepository().save(location);
    }

    @Override
    public QRCodePix gerarQrCodePorLocation(Integer idPessoa, Integer idLocation) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);
        final LocationPix location = getRepository(LocationPixRepository.class).findByIdLocation(idLocation);

        final QRCodePix qrCodePix = resolverQRCodePix(idLocation, location, pessoa);

        return getRepository(QRCodePixRepository.class).save(qrCodePix);
    }

    @Override
    public QRCodePix gerarQrCodePorPagamentoParcela(Integer idPessoa, Integer pagamentoParcelaId) {
        final Pessoa pessoa = getService(PessoaService.class).findAndValidate(idPessoa);
        final PagamentoParcela pagamentoParcela = getService(PagamentoParcelaService.class).findAndValidate(pagamentoParcelaId);

        CobrancaPix cobrancaPix = getService(CobrancaPixService.class).encontrarCobrancaPorPagamentoParcela(pagamentoParcela);
        if (Utils.isEmpty(cobrancaPix)) {
           cobrancaPix = getService(CobrancaPixService.class).gerarRegistoCobrancaPix(pagamentoParcela);
        }

        return resolverQRCodePix(cobrancaPix.getLocation().getIdLocation(), cobrancaPix.getLocation(), pessoa);
    }

    private QRCodePix resolverQRCodePix(Integer idLocation, final LocationPix location, final Pessoa pessoa) {
        final QRCodePix qrCodePix = getRepository(QRCodePixRepository.class).findByLocationId(idLocation);
        if (Utils.isNotEmpty(qrCodePix)) {
            return qrCodePix;
        }
        final PayloadQRCodeResponse payloadQRCodeResponse = clientLocationPix.gerarQRCode(idLocation);
        return gerarQrCodePix(location, pessoa, payloadQRCodeResponse);
    }

    private QRCodePix gerarQrCodePix(final LocationPix location, final Pessoa pessoa, final PayloadQRCodeResponse payloadQRCodeResponse) {
        final QRCodePix qrCodePix = new QRCodePix();

        qrCodePix.setDataAtualizacao(DateUtil.getDate());
        qrCodePix.setDataInclusao(DateUtil.getDate());
        qrCodePix.setLocationPix(location);
        qrCodePix.setQrCode(payloadQRCodeResponse.getQrcode());
        qrCodePix.setPessoa(pessoa);

        return getRepository(QRCodePixRepository.class).save(qrCodePix);
    }

}
