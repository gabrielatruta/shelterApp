package com.example.shelterapp.user.model;

public enum ERole {

    ADMINISTRATOR("ADMINISTRATOR"),
    USER("USER"),
    ONG("ONG");

    private final String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ERole fromValue(String value) {
        for (ERole b : ERole.values()) {
            if (b.name.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
