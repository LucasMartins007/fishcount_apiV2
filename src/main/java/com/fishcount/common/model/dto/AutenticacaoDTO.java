package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class AutenticacaoDTO implements AbstractDTO<Integer> {
    
    private Integer id;

    private String username;

    private String password;
    
    private String token;

    private String refreshToken;

    public AutenticacaoDTO() {
    }

    public AutenticacaoDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }



}
