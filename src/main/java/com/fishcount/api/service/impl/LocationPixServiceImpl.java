package com.fishcount.api.service.impl;

import com.fishcount.api.repository.LocationPixRepository;
import com.fishcount.api.repository.QRCodePixRepository;
import com.fishcount.api.service.LocationPixService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.service.gerencianet.pix.location.ClientLocationPix;
import com.fishcount.common.model.classes.gerencianet.response.PayloadLocationResponse;
import com.fishcount.common.model.classes.gerencianet.response.PayloadQRCodeResponse;
import com.fishcount.common.model.dto.financeiro.pix.LocationPixDTO;
import com.fishcount.common.model.entity.Usuario;
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
    public QRCodePix gerarQrCode(Integer idUsuario, Integer idLocation) {
        final Usuario usuario = getService(UsuarioService.class).findAndValidate(idUsuario);
        final LocationPix location = getRepository(LocationPixRepository.class).findByIdLocation(idLocation);

        final QRCodePix qrCodePix = resolverQRCodePix(idLocation, location, usuario);

        return getRepository(QRCodePixRepository.class).save(qrCodePix);
    }

    private QRCodePix resolverQRCodePix(Integer idLocation, final LocationPix location, final Usuario usuario) {
        final QRCodePix qrCodePix = getRepository(QRCodePixRepository.class).findByLocationId(idLocation);
        if (Utils.isNotEmpty(qrCodePix)) {
            return qrCodePix;
        }
        final PayloadQRCodeResponse payloadQRCodeResponse = clientLocationPix.gerarQRCode(idLocation);
        return gerarQrCodePix(location, usuario, payloadQRCodeResponse);
    }

    private QRCodePix gerarQrCodePix(final LocationPix location, final Usuario usuario, final PayloadQRCodeResponse payloadQRCodeResponse) {
        final QRCodePix qrCodePix = new QRCodePix();

        qrCodePix.setDataAlteracao(DateUtil.getDate());
        qrCodePix.setDataInclusao(DateUtil.getDate());
        qrCodePix.setLocationPix(location);
        qrCodePix.setQrCode(payloadQRCodeResponse.getQrcode());
        qrCodePix.setUsuario(usuario);

        return qrCodePix;
    }

}
