package dev.vetther.payments.lvlup.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class LvlupUserSchema {

    @Getter private String uid;
    @Getter private String fullName;
    @Getter private String username;
    @Getter private String email;
    @Getter private String createdAt;
}
