package dev.vetther.payments.paypal;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.Response;
import dev.vetther.payments.paypal.schema.PaypalWebhooksSchema;
import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PaypalApiWebhookList extends Response {

    private final static String URL = "https://api-m.paypal.com/v1/notifications/webhooks";
    private final static String SANDBOX_URL = "https://api-m.sandbox.paypal.com/v1/notifications/webhooks";

    @Getter private final PaypalWebhooksSchema webhooks;

    PaypalApiWebhookList(String accessToken, boolean sandbox) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create((sandbox ? SANDBOX_URL : URL)))
                .headers("Authorization", "Bearer " + accessToken,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        PaypalWebhooksSchema webhooksSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), PaypalWebhooksSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.webhooks = webhooksSchema;
    }
}
