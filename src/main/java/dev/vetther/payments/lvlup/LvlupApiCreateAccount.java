package dev.vetther.payments.lvlup;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.Response;
import dev.vetther.payments.lvlup.schema.LvlupAccountSchema;
import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LvlupApiCreateAccount extends Response {

    private final static String URL = "https://api.sandbox.lvlup.pro/v4/sandbox/account/new";
    @Getter private final LvlupAccountSchema account;

    LvlupApiCreateAccount() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(URL))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        LvlupAccountSchema accountSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), LvlupAccountSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.account = accountSchema;
    }
}
