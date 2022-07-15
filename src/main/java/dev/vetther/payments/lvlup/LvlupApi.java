package dev.vetther.payments.lvlup;

import java.io.IOException;

public class LvlupApi {

    private final String apiKey;
    private final boolean sandbox;

    public LvlupApi(String apikey, boolean sandbox) {
        this.apiKey = apikey;
        this.sandbox = sandbox;
    }

    public static LvlupApiCreateAccount createSandboxAccount() throws IOException, InterruptedException {
        return new LvlupApiCreateAccount();
    }

    public LvlupApiCreatePayment createPayment(double amount, String redirectUrl, String webhookUrl) throws IOException, InterruptedException {
        return new LvlupApiCreatePayment(amount, redirectUrl, webhookUrl, this.apiKey, this.sandbox);
    }

    public LvlupApiAcceptPayment acceptSandboxPayment(String paymentId) throws IOException, InterruptedException {
        return new LvlupApiAcceptPayment(paymentId, this.apiKey);
    }

    public LvlupApiUserInfo getMe() throws IOException, InterruptedException {
        return new LvlupApiUserInfo(this.apiKey, this.sandbox);
    }
}
