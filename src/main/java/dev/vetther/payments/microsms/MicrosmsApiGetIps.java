package dev.vetther.payments.microsms;

import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MicrosmsApiGetIps {

    @Getter private final String[] ips;

    MicrosmsApiGetIps() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://microsms.pl/psc/ips/"))
                .GET()
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        this.ips = httpResponse.body().split(",");
    }
}
