package dev.vetther.payments.paypal;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.Response;
import dev.vetther.payments.paypal.schema.PaypalOrderSchema;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Locale;

public class PaypalApiCreateOrder extends Response {

    private final static String URL = "https://api-m.paypal.com/v2/checkout/orders";
    private final static String SANDBOX_URL = "https://api-m.sandbox.paypal.com/v2/checkout/orders";

    @Getter private final PaypalOrderSchema order;

    PaypalApiCreateOrder(double amount, String returnUrl, String accessToken, String currencyCode, boolean sandbox) throws IOException, InterruptedException {

        HashMap<String, String> amountMap = new HashMap<>();
        amountMap.put("currency_code", currencyCode);
        amountMap.put("value", String.format(Locale.ROOT, "%.2f", amount));

        JSONObject amountObject = new JSONObject();
        amountObject.put("amount", amountMap);

        JSONArray amountArray = new JSONArray();
        amountArray.put(amountObject);

        JSONObject applicationContext = new JSONObject();
        applicationContext.put("return_url", returnUrl);
        applicationContext.put("shipping_preference", "NO_SHIPPING");

        JSONObject requestBody = new JSONObject();
        requestBody.put("intent", "CAPTURE");
        requestBody.put("purchase_units", amountArray);
        requestBody.put("application_context", applicationContext);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create((sandbox ? SANDBOX_URL : URL)))
                .headers("Authorization", "Bearer " + accessToken,
                        "Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        PaypalOrderSchema orderSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), PaypalOrderSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.order = orderSchema;
    }
}
