package com.training.spotiClone.dao;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private String password;
    private UserType type;

    public User(long id, String name, String password, UserType userType) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = userType;
    }

    public static User createGuestUser() {
        return new User(-1L, "GUEST", "GUEST", UserType.GUEST);
    }
}
