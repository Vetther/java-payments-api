package dev.vetther.payments;

import dev.vetther.payments.lvlup.*;

import java.io.IOException;

public class TestRequest {

    public static void main(String[] args) throws IOException, InterruptedException {

        LvlupApiCreateAccount sandboxAccount = LvlupApi.createSandboxAccount();

        System.out.println("Stworzono sandbox usera: " + sandboxAccount.getBody().getUsername());

        LvlupApi lvlup2 = new LvlupApi(sandboxAccount.getBody().getApiKey(), true);

        System.out.println("Utworzono api dla stworzonego sandbox usera");

        LvlupApiCreatePayment payment = lvlup2.createPayment(2, null, null);

        System.out.println("Utworzono payment stworzonego dla sandbox usera");
        System.out.println("Payment ID: " + payment.getBody().getId());
        System.out.println("Payment URL: " + payment.getBody().getUrl());

        LvlupApiAcceptPayment lvlupApiAcceptPayment = lvlup2.acceptSandboxPayment(payment.getBody().getId());

        System.out.println("Próba zaakceptowania payment dla stworzonego sandbox usera");
        System.out.println("Zwrócono: " + lvlupApiAcceptPayment.isPaymentAccepted());
    }
}
