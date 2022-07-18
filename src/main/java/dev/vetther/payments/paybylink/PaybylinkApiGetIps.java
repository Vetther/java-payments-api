package dev.vetther.payments.paybylink;

import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PaybylinkApiGetIps {

    @Getter private final String[] ips;

    PaybylinkApiGetIps() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://www.rushpay.pl/psc/ips/"))
                .GET()
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        this.ips = httpResponse.body().split(",");
    }
}
