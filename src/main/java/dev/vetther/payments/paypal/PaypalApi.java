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

    /**
     * Creates/gets current bearer token for authentication to REST API
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public PaypalApiCreateToken createToken() throws IOException, InterruptedException {
        return new PaypalApiCreateToken(this.clientId, this.clientSecret, this.sandbox);
    }

    /**
     * Creates a payment for sandbox/real account
     * @param url webhook url, after the event is completed, a POST request sent will be sent to this url
     * @param eventType webhook event, <a href="https://developer.paypal.com/api/rest/webhooks/event-names/">check PayPal event types</a>
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    private PaypalApiCreateWebhook createWebhook(String url, String... eventType) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiCreateWebhook(url, this.accessToken, this.sandbox, eventType);
    }

    /**
     * Gets active webhooks of your sandbox/real app
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public PaypalApiWebhookList getWebhooks() throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiWebhookList(this.accessToken, this.sandbox);
    }

    /**
     * Creates an order for sandbox/real account<br>
     * Additionally creates webhook if not exists with event triggering on webhookUrl (POST) when order is paid
     * @param amount order costs
     * @param redirectUrl URL which will be used to redirect user after order, leave empty to disable
     * @param webhookUrl URL which will receive POST request when order is paid, leave empty to disable
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public PaypalApiCreateOrder createOrder(double amount, String redirectUrl, String webhookUrl) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();

        if (webhookUrl != null && !PaypalUtils.isWebhookCreated(this, webhookUrl, "CHECKOUT.ORDER.APPROVED", "CHECKOUT.ORDER.COMPLETED")) {
            System.out.println("---> NIE ZNALEZIONO WEBHOOKA, TWORZENIE NOWEGO...");
            createWebhook(webhookUrl, "CHECKOUT.ORDER.APPROVED", "CHECKOUT.ORDER.COMPLETED");
        }

        return new PaypalApiCreateOrder(amount, redirectUrl, this.accessToken, this.sandbox);
    }

    /**
     * Return information about sandbox/real order
     * @param orderId order id
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public PaypalApiOrderInfo getOrderInfo(String orderId) throws IOException, InterruptedException {
        this.accessToken = createToken().getToken().getAccess_token();
        return new PaypalApiOrderInfo(orderId, this.accessToken, this.sandbox);
    }
}
