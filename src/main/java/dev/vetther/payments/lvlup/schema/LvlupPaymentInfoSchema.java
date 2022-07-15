package dev.vetther.payments.lvlup.schema;

import lombok.Getter;

public class LvlupPaymentInfoSchema {

    @Getter private final String amountStr;
    @Getter private final String amountWithFeeStr;
    @Getter private final int amountInt;
    @Getter private final int amountWithFeeInt;
    @Getter private final boolean payed;

    private LvlupPaymentInfoSchema(String amountStr, String amountWithFeeStr, int amountInt, int amountWithFeeInt, boolean payed) {
        this.amountStr = amountStr;
        this.amountWithFeeStr = amountWithFeeStr;
        this.amountInt = amountInt;
        this.amountWithFeeInt = amountWithFeeInt;
        this.payed = payed;
    }
}
