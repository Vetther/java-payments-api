package dev.vetther.payments.paypal.schema;

import lombok.Getter;

import java.util.ArrayList;

public class PaypalWebhooks {

    @Getter private final ArrayList<PaypalWebhook> webhooks;

    private PaypalWebhooks(ArrayList<PaypalWebhook> webhooks) {
        this.webhooks = webhooks;
    }
}
