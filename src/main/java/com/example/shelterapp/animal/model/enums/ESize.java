package com.example.shelterapp.animal.model.enums;

public enum ESize {

    SMALL("small"),
    MEDIUM("medium"),
    BIG("big"),
    LARGE("large");

    private final String name;

    ESize(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ESize fromValue(String value) {
        for (ESize b : ESize.values()) {
            if (b.name.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
