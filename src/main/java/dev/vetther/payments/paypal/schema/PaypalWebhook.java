package dev.vetther.payments.paypal.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

public class PaypalWebhook {

    @Getter private final String id;
    @Getter private final String url;
    @Getter private final ArrayList<LinkDescription> links;
    @Getter private final ArrayList<EventType> event_types;

    private PaypalWebhook(String id, String url, ArrayList<LinkDescription> links, ArrayList<EventType> event_types) {
        this.id = id;
        this.url = url;
        this.links = links;
        this.event_types = event_types;
    }

    @AllArgsConstructor
    public static class LinkDescription {
        @Getter private final String href;
        @Getter private final String rel;
        @Getter private final String method;
    }

    @AllArgsConstructor
    public static class EventType {
        @Getter private final String name;
        @Getter private final String description;
    }
}
