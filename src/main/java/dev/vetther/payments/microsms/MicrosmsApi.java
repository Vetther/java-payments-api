package dev.vetther.payments.microsms;

import java.io.IOException;
import java.util.List;

public class MicrosmsApi {

    private final String shopId;
    private final String shopHash;

    public MicrosmsApi(String shopId, String shopHash) {
        this.shopId = shopId;
        this.shopHash = shopHash;
    }

    /**
     * Creates a payment for account
     * @param amount payment costs
     * @param redirectUrl URL which will be used to redirect user after payment, leave empty to disable
     * @param webhookUrl URL which will receive POST request when payment is done, leave empty to disable
     * @param shopHashMode MicroSMS user HashMode (SHA256 or MD5), check it in MicroSMS panel
     * @param additionalInfo Additional parameter for your shop (e.g. for your order id), will be available for use in the POST webhook
     */
    public MicrosmsApiCreatePayment createPayment(double amount, String redirectUrl, String webhookUrl, MicrosmsHash shopHashMode, String additionalInfo) {
        return new MicrosmsApiCreatePayment(amount, this.shopId, this.shopHash, redirectUrl, webhookUrl, shopHashMode, additionalInfo);
    }

    /**
     * Gets an ip list used by microsms to send http request to webhookUrl<br>
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public MicrosmsApiGetIps getIps() throws IOException, InterruptedException {
        return new MicrosmsApiGetIps();
    }

    /**
     * Checks if ip is one of the MicroSMS service ips<br>
     * Use it to authentication in your webhook url controller to check, if payment status was sent by MicroSMS, not by someone else
     * @param ip Any ip address to check
     * @throws IOException if an I/O error occurs when sending or receiving http request
     * @throws InterruptedException if the operation is interrupted
     */
    public boolean isCorrectIp(String ip) throws IOException, InterruptedException {
        return List.of(new MicrosmsApiGetIps().getIps()).contains(ip);
    }
}
