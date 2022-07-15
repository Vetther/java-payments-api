package dev.vetther.payments.paypal.schema;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

public class PaypalOrderSchema {

    @Getter private final String create_time;
    @Getter private final String id;
    @Getter private final String intent;
    @Getter private final Payer payer;
    @Getter private final String processing_instruction;
    @Getter private final String status;
    @Getter private final String update_time;
    @Getter private final ArrayList<LinkDescription> links;

    public LinkDescription getUrl() {
        return this.links.get(1);
    }

    public boolean isPaid() {
        return status.equalsIgnoreCase("APPROVED") || status.equalsIgnoreCase("COMPLETED");
    }

    private PaypalOrderSchema(String create_time, String id, String intent, Payer payer, String processing_instruction, String status, String update_time, ArrayList<LinkDescription> links) {
        this.create_time = create_time;
        this.id = id;
        this.intent = intent;
        this.payer = payer;
        this.processing_instruction = processing_instruction;
        this.status = status;
        this.update_time = update_time;
        this.links = links;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LinkDescription {
        @Getter private final String href;
        @Getter private final String rel;
        @Getter private final String method;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Payer {
        @Getter private final String email_address;
        @Getter private final String payer_id;
        @Getter private final String birth_date;
        @Getter private final PayerName name;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PayerName {
        @Getter private final String full_name;
        @Getter private final String given_name;
        @Getter private final String middle_name;
        @Getter private final String prefix;
        @Getter private final String suffix;
        @Getter private final String surname;
    }
}
