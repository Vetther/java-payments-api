package dev.vetther.payments.lvlup;

import java.io.IOException;

public class LvlupApi {

    private final String apiKey;
    private final boolean sandbox;

    public LvlupApi(String apikey, boolean sandbox) {
        this.apiKey = apikey;
        this.sandbox = sandbox;
    }

    /**
     * Creates a sandbox account
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public static LvlupApiCreateAccount createSandboxAccount() throws IOException, InterruptedException {
        return new LvlupApiCreateAccount();
    }

    /**
     * Creates a payment for sandbox/real account
     * @param amount payment costs
     * @param redirectUrl URL which will be used to redirect user after payment, leave empty to disable
     * @param webhookUrl URL which will receive POST request when payment is done, leave empty to disable
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public LvlupApiCreatePayment createPayment(double amount, String redirectUrl, String webhookUrl) throws IOException, InterruptedException {
        return new LvlupApiCreatePayment(amount, redirectUrl, webhookUrl, this.apiKey, this.sandbox);
    }

    /**
     * Accept payment for ONLY sandbox account
     * @param paymentId payment id
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public LvlupApiAcceptPayment acceptSandboxPayment(String paymentId) throws IOException, InterruptedException {
        return new LvlupApiAcceptPayment(paymentId, this.apiKey);
    }

    /**
     * Return information about us
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public LvlupApiUserInfo getMe() throws IOException, InterruptedException {
        return new LvlupApiUserInfo(this.apiKey, this.sandbox);
    }

    /**
     * Return information about sandbox/real payment
     * @param paymentId payment id
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public LvlupApiPaymentInfo getPaymentInfo(String paymentId) throws IOException, InterruptedException {
        return new LvlupApiPaymentInfo(paymentId, this.apiKey, this.sandbox);
    }
}
