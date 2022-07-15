package dev.vetther.payments.lvlup;

import dev.vetther.payments.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LvlupApiAcceptPayment extends Response {

    private final static String URL = "https://api.sandbox.lvlup.pro/v4/sandbox/wallet/up/:ID/ok";

    LvlupApiAcceptPayment(String paymentId, String apikey) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(URL.replace(":ID", paymentId)))
                .header("Authorization", "Bearer " + apikey)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
    }

    public boolean isPaymentAccepted() {
        return this.getStatusCode() == 200;
    }
}
