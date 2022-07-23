package dev.vetther.payments.paybylink.schema;

import lombok.Getter;

public class PaybylinkPaymentCreateSchema {

    @Getter private final String url;
    @Getter private final String transactionId;
    @Getter private final String error;

    private PaybylinkPaymentCreateSchema(String url, String transactionId, String error) {
        this.url = url;
        this.transactionId = transactionId;
        this.error = error;
    }
}
