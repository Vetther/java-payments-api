package dev.vetther.payments.paybylink;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.PaymentHash;
import dev.vetther.payments.Response;
import dev.vetther.payments.lvlup.schema.LvlupPaymentSchema;
import dev.vetther.payments.paybylink.schema.PaybylinkPaymentSchema;
import dev.vetther.payments.util.SignatureUtils;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PaybylinkCreatePayment extends Response {

    @Getter private final PaybylinkPaymentSchema payment;

    PaybylinkCreatePayment(int shopId, PaymentHash shopHashMode, float amount, String control, String description, String email, String webhookUrl, String redirectUrl, boolean hideReceiver) throws IOException, InterruptedException {

        String amountStr = String.format(Locale.ROOT, "%.2f", amount);

        String signature = SignatureUtils.generateSignature(shopHashMode, "|", shopId, amountStr, control, description, email, webhookUrl, redirectUrl, hideReceiver);

        JSONObject requestBody = new JSONObject();
        requestBody.put("shopId", shopId);
        requestBody.put("price", amount);
        requestBody.put("signature", signature);
        if (control != null)        requestBody.put("control", control);
        if (description != null)    requestBody.put("description", description);
        if (email != null)          requestBody.put("email", email);
        if (webhookUrl != null)     requestBody.put("notifyURL", webhookUrl);
        if (redirectUrl != null)    requestBody.put("returnUrlSuccess", redirectUrl);
        if (hideReceiver)           requestBody.put("hideReceiver", true);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://secure.paybylink.pl/api/v1/transfer/generate"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        PaybylinkPaymentSchema paymentSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), PaybylinkPaymentSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.payment = paymentSchema;

    }
}
