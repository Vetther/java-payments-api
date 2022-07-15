package dev.vetther.payments.lvlup.schema;

import lombok.Getter;

public class LvlupAccountSchema {

    @Getter private final String apiKey;
    @Getter private final String email;
    @Getter private final String password;
    @Getter private final String username;
    @Getter private final int id;

    private LvlupAccountSchema(String apiKey, String email, String password, String username, int id) {
        this.apiKey = apiKey;
        this.email = email;
        this.password = password;
        this.username = username;
        this.id = id;
    }
}
