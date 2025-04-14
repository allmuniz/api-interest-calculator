package com.allmuniz.api_interest_calculator.enums;

import lombok.Getter;

@Getter
public enum InterestType {
    SIMPLE(1, "Simple"),
    COMPOUND(2, "Compound");

    private final int id;
    private final String description;

    InterestType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static InterestType fromId(int id) {
        for (InterestType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid InterestType id: " + id);
    }
}

