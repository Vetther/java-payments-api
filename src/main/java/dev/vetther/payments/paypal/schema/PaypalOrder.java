package dev.vetther.payments.paypal.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
public class PaypalOrder {

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

    @AllArgsConstructor
    public static class LinkDescription {
        @Getter private final String href;
        @Getter private final String rel;
        @Getter private final String method;
    }

    @AllArgsConstructor
    public static class Payer {
        @Getter private final String email_address;
        @Getter private final String payer_id;
        @Getter private final String birth_date;
        @Getter private final PayerName name;
    }

    @AllArgsConstructor
    public static class PayerName {
        @Getter private final String full_name;
        @Getter private final String given_name;
        @Getter private final String middle_name;
        @Getter private final String prefix;
        @Getter private final String suffix;
        @Getter private final String surname;
    }
}
