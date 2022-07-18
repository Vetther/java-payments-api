package dev.vetther.payments.util;

import dev.vetther.payments.PaymentHash;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class SignatureUtils {

    public static String generateSignature(PaymentHash paymentHash, String delimiter, Object... arguments) {

        List<String> toSignature = new ArrayList<>();

        for (Object argument : arguments) {
            if (argument != null) toSignature.add(String.valueOf(argument));
        }

        return (paymentHash == PaymentHash.SHA256)
                ? DigestUtils.sha256Hex(String.join(delimiter, toSignature))
                : DigestUtils.md5Hex(String.join(delimiter, toSignature));
    }
}
