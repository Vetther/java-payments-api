package dev.vetther.payments.util;

import dev.vetther.payments.paypal.PaypalApi;
import dev.vetther.payments.paypal.schema.PaypalWebhookSchema;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PaypalUtils {

    public static boolean isWebhookCreated(PaypalApi paypalApi, String webhookUrl, String... eventType) throws IOException, InterruptedException {
        for (PaypalWebhookSchema webhook : paypalApi.getWebhooks().getWebhooks().getWebhooks()) {

            if (!webhook.getUrl().equalsIgnoreCase(webhookUrl)) {
                continue;
            }

            if (webhook.getEvent_types()
                    .stream()
                    .map(PaypalWebhookSchema.EventType::getName)
                    .collect(Collectors.toSet())
                    .containsAll(List.of(eventType))) {
                return true;
            }
        }
        return false;
    }
}
