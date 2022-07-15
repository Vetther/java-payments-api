package dev.vetther.payments;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import dev.vetther.payments.lvlup.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class TestRequest {

    public static void main(String[] args) throws IOException, InterruptedException {

        LvlupApiCreateAccount sandboxAccount = LvlupApi.createSandboxAccount();

        System.out.println("Stworzono sandbox usera: " + sandboxAccount.getAccount().getUsername());

        LvlupApi lvlup2 = new LvlupApi(sandboxAccount.getAccount().getApiKey(), true);

        LvlupApiUserInfo me = lvlup2.getMe();

        System.out.println("UserID: " + me.getMe().getUid());

        System.out.println("Utworzono api dla stworzonego sandbox usera");

        LvlupApiCreatePayment payment = lvlup2.createPayment(2, null, null);

        System.out.println("Utworzono payment stworzonego dla sandbox usera");
        System.out.println("Payment ID: " + payment.getPayment().getId());
        System.out.println("Payment URL: " + payment.getPayment().getUrl());

        LvlupApiAcceptPayment lvlupApiAcceptPayment = lvlup2.acceptSandboxPayment(payment.getPayment().getId());

        System.out.println("Próba zaakceptowania payment dla stworzonego sandbox usera");
        System.out.println("Zwrócono: " + lvlupApiAcceptPayment.isPaymentAccepted());

        System.out.println("Informacje dotyczące płatności:");

        LvlupApiPaymentInfo paymentInfo = lvlup2.getPaymentInfo(payment.getPayment().getId());

        System.out.println(paymentInfo.getPayment().getAmountInt());
        System.out.println(paymentInfo.getPayment().getAmountStr());
        System.out.println(paymentInfo.getPayment().getAmountWithFeeInt());
        System.out.println(paymentInfo.getPayment().getAmountWithFeeStr());
        System.out.println(paymentInfo.getPayment().isPayed());

    }
}
