package dev.vetther.payments.lvlup;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vetther.payments.Response;
import dev.vetther.payments.lvlup.schema.LvlupPaymentSchema;
import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class LvlupApiCreatePayment extends Response {

    private final static String URL = "https://api.lvlup.pro/v4/wallet/up";
    private final static String SANDBOX_URL = "https://api.sandbox.lvlup.pro/v4/wallet/up";

    @Getter private final LvlupPaymentSchema body;

    LvlupApiCreatePayment(
            double amount,
            String redirectUrl,
            String webhookUrl,
            String apikey,
            boolean sandbox) throws IOException, InterruptedException {

        JSONObject requestBody = new JSONObject();

        requestBody.put("amount", String.format(Locale.ROOT, "%.2f", amount));
        if (redirectUrl != null) requestBody.put("redirectUrl", redirectUrl);
        if (webhookUrl != null) requestBody.put("webhookUrl", webhookUrl);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(sandbox ? SANDBOX_URL : URL))
                .header("Authorization", "Bearer " + apikey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        LvlupPaymentSchema paymentSchema = new ObjectMapper().readValue(jsonObject.toString(), LvlupPaymentSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.body = paymentSchema;
    }
}
