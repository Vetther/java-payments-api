package dev.vetther.payments.hotpay;

import dev.vetther.payments.util.FormUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HotpayApiCreatePayment {

    @Getter private final String url;

    HotpayApiCreatePayment(String secret, double amount, String itemName, String redirectUrl, String paymentId) {

        String amountStr = String.format(Locale.ROOT, "%.2f", amount);

        Map<String, String> formMap = new HashMap<>();
        formMap.put("SEKRET", secret);
        formMap.put("KWOTA", amountStr);
        formMap.put("NAZWA_USLUGI", itemName);
        formMap.put("ADRES_WWW", redirectUrl);
        formMap.put("ID_ZAMOWIENIA", paymentId);

        this.url = "https://platnosc.hotpay.pl?" + FormUtils.createForm(formMap);
    }
}
