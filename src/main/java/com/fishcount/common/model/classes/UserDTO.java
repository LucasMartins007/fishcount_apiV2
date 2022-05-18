package com.fishcount.common.model.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fishcount.common.model.pattern.annotations.converter.TransientFieldDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class UserDTO implements Serializable {
    
    private Integer id;

    private String username;

    private String password;
    
    private String token;

    private String refreshToken;

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }



}
