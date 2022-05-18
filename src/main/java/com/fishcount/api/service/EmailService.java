package com.fishcount.api.service;

import com.fishcount.api.service.impl.interfaces.IAbstractService;
import com.fishcount.common.model.dto.EmailDTO;
import com.fishcount.common.model.entity.Email;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author lucas
 */
@Service
public interface EmailService extends IAbstractService<Email, Integer, EmailDTO> {

    Email incluir(Email email);

    Optional<Email> findByEmail(Email email);

}
