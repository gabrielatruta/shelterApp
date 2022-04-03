package com.example.shelterapp.animal.model.enums;

public enum EColor {
    BLACK("black"),
    WHITE("white"),
    GREY("grey"),
    BROWN("brown"),
    YELLOW("yellow"),
    RED("red"),
    ORANGE("orange");

    private final String name;

    EColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EColor fromValue(String value) {
        for (EColor b : EColor.values()) {
            if (b.name.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
