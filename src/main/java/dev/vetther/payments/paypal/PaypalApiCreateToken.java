package dev.vetther.payments.paypal;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.Response;
import dev.vetther.payments.paypal.schema.PaypalAccessTokenSchema;
import dev.vetther.payments.util.FormUtils;
import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Collections;

public class PaypalApiCreateToken extends Response {

    private final static String URL = "https://api-m.paypal.com/v1/oauth2/token";
    private final static String SANDBOX_URL = "https://api-m.sandbox.paypal.com/v1/oauth2/token";

    @Getter private final PaypalAccessTokenSchema token;

    PaypalApiCreateToken(String clientId, String clientSecret, boolean sandbox) throws IOException, InterruptedException {

        String requestBody = FormUtils.createForm(
                Collections.singletonMap("grant_type", "client_credentials")
        );

        String auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create((sandbox ? SANDBOX_URL : URL)))
                .headers("Authorization", "Basic " + auth,
                        "Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        PaypalAccessTokenSchema tokenSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), PaypalAccessTokenSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.token = tokenSchema;
    }
}
