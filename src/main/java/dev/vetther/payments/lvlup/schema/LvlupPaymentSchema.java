package dev.vetther.payments.lvlup.schema;

import lombok.Getter;

public class LvlupPaymentSchema {

    @Getter private final String id;
    @Getter private final String url;

    private LvlupPaymentSchema(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
