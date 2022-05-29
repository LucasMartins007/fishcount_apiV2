package com.fishcount.common.model.classes.gerencianet.authentication;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadToken {

    private String access_token;

    private String token_type;

    private Integer expires_in;

    private String scope;
}
