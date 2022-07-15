package dev.vetther.payments.paypal.schema;

import lombok.Getter;

public class PaypalAccessToken {

    @Getter private final String scope;
    @Getter private final String access_token;
    @Getter private final String token_type;
    @Getter private final String app_id;
    @Getter private final int expires_in;
    @Getter private final String nonce;

    private PaypalAccessToken(String scope, String access_token, String token_type, String app_id, int expires_in, String nonce) {
        this.scope = scope;
        this.access_token = access_token;
        this.token_type = token_type;
        this.app_id = app_id;
        this.expires_in = expires_in;
        this.nonce = nonce;
    }
}
