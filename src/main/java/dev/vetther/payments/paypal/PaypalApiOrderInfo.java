package dev.vetther.payments.paypal;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.Response;
import dev.vetther.payments.paypal.schema.PaypalOrderSchema;
import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PaypalApiOrderInfo extends Response {

    private final static String URL = "https://api-m.paypal.com/v2/checkout/orders/:ID";
    private final static String SANDBOX_URL = "https://api-m.sandbox.paypal.com/v2/checkout/orders/:ID";

    @Getter private final PaypalOrderSchema order;

    PaypalApiOrderInfo(String orderId, String accessToken, boolean sandbox) throws IOException, InterruptedException {

        URI uri = URI.create((sandbox ? SANDBOX_URL : URL).replace(":ID", orderId));

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .headers("Authorization", "Bearer " + accessToken,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        PaypalOrderSchema orderSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), PaypalOrderSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.order = orderSchema;
    }
}
