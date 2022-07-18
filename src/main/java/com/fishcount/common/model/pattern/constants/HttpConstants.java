package com.fishcount.common.model.pattern.constants;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpConstants {

    public static final String CLIENT_CREDENTALS = "{\"grant_type\": \"client_credentials\"}";

    public static final String BEARER_AUTH = "Bearer ";

    public static final String HEADER_AUTHORIZATION = "Authorization";
}
