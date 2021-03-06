package com.example.shelterapp.animal.model.enums;

public enum ECharacteristics {

    DOTTED("DOTTED"),
    STRIPED("STRIPED"),
    MULTICOLOURED("MULTICOLOURED"),
    HAIRLESS("HAIRLESS");

    private final String name;

    ECharacteristics(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ECharacteristics fromValue(String value) {
        for (ECharacteristics b : ECharacteristics.values()) {
            if (b.name.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
