package dev.vetther.payments.paypal;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.Response;
import dev.vetther.payments.paypal.schema.PaypalOrder;
import dev.vetther.payments.paypal.schema.PaypalWebhook;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PaypalApiCreateWebhook extends Response {

    private final static String URL = "https://api-m.paypal.com/v1/notifications/webhooks";
    private final static String SANDBOX_URL = "https://api-m.sandbox.paypal.com/v1/notifications/webhooks";

    @Getter private final PaypalWebhook webhook;

    PaypalApiCreateWebhook(String url, String accessToken, boolean sandbox, String... eventType) throws IOException, InterruptedException {

        JSONArray eventsArray = new JSONArray();
        for (String type : eventType) {
            JSONObject event = new JSONObject();
            event.put("name", type);
            eventsArray.put(event);
        }

        JSONObject webhook = new JSONObject();
        webhook.put("event_types", eventsArray);
        webhook.put("url", url);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create((sandbox ? SANDBOX_URL : URL)))
                .headers("Authorization", "Bearer " + accessToken,
                        "Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(webhook.toString()))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        PaypalWebhook webhookSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), PaypalWebhook.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.webhook = webhookSchema;
    }
}
