package dev.vetther.payments.paybylink;

import dev.vetther.payments.PaymentHash;
import dev.vetther.payments.microsms.MicrosmsApiGetIps;

import java.io.IOException;
import java.util.List;

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

    public PaybylinkCreatePsc createPSC(int userId, int pin, float amount, String redirectUrlSuccess, String redirectUrlFailure, String webhookUrl, String additionalInfo) throws IOException, InterruptedException {
        return new PaybylinkCreatePsc(userId, this.shopId, pin, amount, redirectUrlSuccess, redirectUrlFailure, webhookUrl, additionalInfo);
    }

    /**
     * Gets an ip list used by PayByLink to send http request to webhookUrl (only PSC!)<br>
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public PaybylinkApiGetIps getPSCIps() throws IOException, InterruptedException {
        return new PaybylinkApiGetIps();
    }

    /**
     * Checks if ip is one of the PayByLink service ips (only PSC!)<br>
     * Use it to authentication in your webhook url controller to check, if payment status was sent by PayByLink, not by someone else
     * @param ip Any ip address to check
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public boolean isCorrectPSCIp(String ip) throws IOException, InterruptedException {
        return List.of(new PaybylinkApiGetIps().getIps()).contains(ip);
    }
}
