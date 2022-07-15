package dev.vetther.payments;

import lombok.Getter;

import java.net.http.HttpHeaders;

public abstract class Response {

    @Getter private int statusCode;
    @Getter private HttpHeaders headers;

    protected void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    protected void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
