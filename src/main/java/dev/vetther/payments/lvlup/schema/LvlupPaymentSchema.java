package dev.vetther.payments.lvlup.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class LvlupPaymentSchema {

    @Getter private String id;
    @Getter private String url;
}
