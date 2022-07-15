package dev.vetther.payments.paypal;

import java.io.IOException;

public class PaypalApi {

    private final String clientId;
    private final String clientSecret;
    private final boolean sandbox;
    private String accessToken;

    public PaypalApi(String clientId, String clientSecret, boolean sandbox) throws IOException, InterruptedException {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.sandbox = sandbox;
        this.accessToken = createToken().getToken().getAccess_token();
    }

    private PaypalApiCreateToken createToken() throws IOException, InterruptedException {
        return new PaypalApiCreateToken(this.clientId, this.clientSecret, this.sandbox);
    }

    public PaypalApiCreateOrder createOrder(double amount, String returnUrl) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiCreateOrder(amount, returnUrl, this.accessToken, this.sandbox);
    }

    public PaypalApiOrderInfo getOrderInfo(String orderId) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiOrderInfo(orderId, this.accessToken, this.sandbox);
    }
}
