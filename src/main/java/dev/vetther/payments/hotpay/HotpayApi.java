package dev.vetther.payments.hotpay;

public class HotpayApi {

    private final String secret;
    private final String notificationPassword;

    public HotpayApi(String secret, String notificationPassword) {
        this.secret = secret;
        this.notificationPassword = notificationPassword;
    }

    public HotpayApiCreatePayment createPayment(double amount, String itemName, String redirectUrl, String paymentId) {
        return new HotpayApiCreatePayment(this.secret, amount, itemName, redirectUrl, paymentId);
    }

    public HotpayApiCreatePsc createPSC(double amount, String itemName, String redirectUrl, String paymentId) {
        return new HotpayApiCreatePsc(this.secret, amount, itemName, redirectUrl, paymentId);
    }

    public HotpayApiCreateSms createDirectBilling(double amount, String redirectUrlSuccess, String redirectUrlFailure, String paymentId) {
        return new HotpayApiCreateSms(this.secret, amount, redirectUrlSuccess, redirectUrlFailure, paymentId);
    }
}
