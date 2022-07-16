package dev.vetther.payments.hotpay;

import dev.vetther.payments.util.FormUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HotpayApiCreateSms {

    @Getter private final String url;

    HotpayApiCreateSms(String secret, double amount, String redirectUrlSuccess, String redirectUrlFailure, String paymentId) {

        String amountStr = String.format(Locale.ROOT, "%.2f", amount);

        Map<String, String> formMap = new HashMap<>();
        formMap.put("SEKRET", secret);
        formMap.put("KWOTA", amountStr);
        if (redirectUrlSuccess != null) formMap.put("PRZEKIEROWANIE_SUKCESS", redirectUrlSuccess);
        if (redirectUrlFailure != null) formMap.put("PRZEKIEROWANIE_BLAD", redirectUrlFailure);
        formMap.put("ID_ZAMOWIENIA", paymentId);

        this.url = "https://directbilling.hotpay.pl/?" + FormUtils.createForm(formMap);
    }
}
