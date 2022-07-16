package dev.vetther.payments.microsms;

import dev.vetther.payments.util.FormUtils;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MicrosmsApiCreatePayment {

    @Getter private final String url;

    MicrosmsApiCreatePayment(double amount, String shopId, String shopHash, String redirectUrl, String webhookUrl, MicrosmsHash shopHashMode, String additionalInfo) {

        String amountStr = String.format(Locale.ROOT, "%.2f", amount);

        String signature = (shopHashMode == MicrosmsHash.SHA256)
                ? DigestUtils.sha256Hex(shopId + shopHash + amountStr)
                : DigestUtils.md5Hex(shopId + shopHash + amountStr);

        Map<String, String> formMap = new HashMap<>();

        formMap.put("shopid", shopId);
        formMap.put("signature", signature);
        formMap.put("amount", amountStr);
        if (additionalInfo != null) formMap.put("control", additionalInfo);
        if (webhookUrl != null) formMap.put("return_urlc", webhookUrl);
        if (redirectUrl != null) formMap.put("return_url", redirectUrl);

        String form = FormUtils.createForm(formMap);

        this.url = "https://microsms.pl/api/bankTransfer?" + form;
    }
}
