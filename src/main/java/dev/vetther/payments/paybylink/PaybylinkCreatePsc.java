package dev.vetther.payments.paybylink;

import dev.vetther.payments.PaymentHash;
import dev.vetther.payments.util.FormUtils;
import dev.vetther.payments.util.SignatureUtils;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PaybylinkCreatePsc {

    @Getter private final String url;

    PaybylinkCreatePsc(int userId, int shopId, int pin, float amount, String redirectUrlSuccess, String redirectUrlFailure, String webhookUrl, String control) throws IOException, InterruptedException {

        String amountStr = String.format(Locale.ROOT, "%.2f", amount);

        String signature = SignatureUtils.generateSignature(PaymentHash.SHA256, "", userId, pin, amountStr);

        Map<String, String> params = new HashMap<>();
        params.put("userid", String.valueOf(userId));
        params.put("shopid", String.valueOf(shopId));
        params.put("amount", amountStr);
        params.put("return_ok", redirectUrlSuccess);
        params.put("return_fail", redirectUrlFailure);
        params.put("url", webhookUrl);
        params.put("control", control);
        params.put("hash", signature);

        this.url = "https://www.rushpay.pl/api/psc?" + FormUtils.createForm(params);
    }
}
