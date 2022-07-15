package dev.vetther.payments.paypal;

import dev.vetther.payments.util.PaypalUtils;

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

    public PaypalApiCreateToken createToken() throws IOException, InterruptedException {
        return new PaypalApiCreateToken(this.clientId, this.clientSecret, this.sandbox);
    }

    private PaypalApiCreateWebhook createWebhook(String url, String... eventType) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiCreateWebhook(url, this.accessToken, this.sandbox, eventType);
    }

    public PaypalApiWebhookList getWebhooks() throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiWebhookList(this.accessToken, this.sandbox);
    }

    public PaypalApiCreateOrder createOrder(double amount, String returnUrl, String webhookUrl) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();

        if (!PaypalUtils.isWebhookCreated(this, webhookUrl, "CHECKOUT.ORDER.APPROVED", "CHECKOUT.ORDER.COMPLETED")) {
            System.out.println("---> NIE ZNALEZIONO WEBHOOKA, TWORZENIE NOWEGO...");
            createWebhook(webhookUrl, "CHECKOUT.ORDER.APPROVED", "CHECKOUT.ORDER.COMPLETED");
        }

        return new PaypalApiCreateOrder(amount, returnUrl, this.accessToken, this.sandbox);
    }

    public PaypalApiOrderInfo getOrderInfo(String orderId) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiOrderInfo(orderId, this.accessToken, this.sandbox);
    }
}
