package dev.vetther.payments.paybylink;

import dev.vetther.payments.PaymentHash;
import dev.vetther.payments.microsms.MicrosmsHash;

import java.io.IOException;

public class PaybylinkApi {

    private final int shopId;
    private final String shopHash;

    public PaybylinkApi(int shopId, String shopHash) {
        this.shopId = shopId;
        this.shopHash = shopHash;
    }

    public PaybylinkCreatePayment createPayment(float amount, String redirectUrl, String webhookUrl, String description, String email, PaymentHash shopHashMode, String additionalInfo, boolean hideReceiver) throws IOException, InterruptedException {
        return new PaybylinkCreatePayment(this.shopId, shopHashMode, amount, additionalInfo, description, email, webhookUrl, redirectUrl, hideReceiver);
    }
}
