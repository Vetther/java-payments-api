package dev.vetther.payments.lvlup.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class LvlupPaymentInfoSchema {

    @Getter private String amountStr;
    @Getter private String amountWithFeeStr;
    @Getter private int amountInt;
    @Getter private int amountWithFeeInt;
    @Getter private boolean payed;
}
