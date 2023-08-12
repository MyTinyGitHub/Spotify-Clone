package com.training.spotiClone.dao;

public enum UserType {
    GUEST(0),
    NORMAL(1),
    PREMIUM(2);

    private final int id;
    private UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static UserType valueOf(int id) {
        return switch (id) {
            case 0 -> UserType.GUEST;
            case 1 -> UserType.NORMAL;
            case 2 -> UserType.PREMIUM;
            default -> throw new IllegalArgumentException();
        };
    }
}
