package dev.vetther.payments.lvlup.schema;

import lombok.Getter;

public class LvlupUserSchema {

    @Getter private final String uid;
    @Getter private final String fullName;
    @Getter private final String username;
    @Getter private final String email;
    @Getter private final String createdAt;

    private LvlupUserSchema(String uid, String fullName, String username, String email, String createdAt) {
        this.uid = uid;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }
}
