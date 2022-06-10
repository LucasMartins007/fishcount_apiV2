package com.fishcount.common.model.pattern.authentication;

/**
 *
 * @author Lucas Martins
 */
public enum HttpsTLSProtocol {

    TLS1("TLSv1"),
    TLS11("TLSv1.1"),
    TLS12("TLSv1.2");
//    TLS13("TLSv1.3");

    private final String name;

    private HttpsTLSProtocol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
