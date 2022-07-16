package dev.vetther.payments.paybylink.schema;

import lombok.Getter;

public class PaybylinkPaymentSchema {

    @Getter private final String transactionId;
    @Getter private final String control;
    @Getter private final String email;
    @Getter private final float amountPaid;
    @Getter private final int notificationAttempt;
    @Getter private final String paymentType;
    @Getter private final int apiVersion;
    @Getter private final int signature;
    @Getter private final String error;

    private PaybylinkPaymentSchema(String transactionId, String control, String email, float amountPaid, int notificationAttempt, String paymentType, int apiVersion, int signature, String error) {
        this.transactionId = transactionId;
        this.control = control;
        this.email = email;
        this.amountPaid = amountPaid;
        this.notificationAttempt = notificationAttempt;
        this.paymentType = paymentType;
        this.apiVersion = apiVersion;
        this.signature = signature;
        this.error = error;
    }
}
