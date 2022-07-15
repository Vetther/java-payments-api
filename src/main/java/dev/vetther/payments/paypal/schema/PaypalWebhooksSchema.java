package dev.vetther.payments.paypal.schema;

import lombok.Getter;

import java.util.ArrayList;

public class PaypalWebhooksSchema {

    @Getter private final ArrayList<PaypalWebhookSchema> webhooks;

    private PaypalWebhooksSchema(ArrayList<PaypalWebhookSchema> webhooks) {
        this.webhooks = webhooks;
    }
}
