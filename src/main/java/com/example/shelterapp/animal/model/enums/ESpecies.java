package com.example.shelterapp.animal.model.enums;

public enum ESpecies {

    DOG("DOG"),
    CAT("CAT");

    private final String name;

    ESpecies(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ESpecies fromValue(String value) {
        for (ESpecies b : ESpecies.values()) {
            if (b.name.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
