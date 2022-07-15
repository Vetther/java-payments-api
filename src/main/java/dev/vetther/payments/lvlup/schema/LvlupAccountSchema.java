package dev.vetther.payments.lvlup.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class LvlupAccountSchema {

    @Getter private String apiKey;
    @Getter private String email;
    @Getter private String password;
    @Getter private String username;
    @Getter private int id;
}
