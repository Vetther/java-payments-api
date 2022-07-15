package dev.vetther.payments.lvlup;

import com.google.gson.GsonBuilder;
import dev.vetther.payments.Response;
import dev.vetther.payments.lvlup.schema.LvlupUserSchema;
import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LvlupApiUserInfo extends Response {

    private final static String URL = "https://api.lvlup.pro/v4/me";
    private final static String SANDBOX_URL = "https://api.sandbox.lvlup.pro/v4/me";

    @Getter private final LvlupUserSchema me;

    LvlupApiUserInfo(String apikey, boolean sandbox) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(sandbox ? SANDBOX_URL : URL))
                .header("Authorization", "Bearer " + apikey)
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        LvlupUserSchema userSchema = new GsonBuilder().create().fromJson(jsonObject.toString(), LvlupUserSchema.class);

        this.setStatusCode(httpResponse.statusCode());
        this.setHeaders(httpResponse.headers());
        this.me = userSchema;
    }
}
