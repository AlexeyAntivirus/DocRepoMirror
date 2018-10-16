package com.revex.docrepo.database.utils;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

public enum SemesterType {
    AUTUMN("Осінній"),
    SPRING("Весняний");

    private final String ukrainianValue;

    SemesterType(String ukrainianValue) {
        this.ukrainianValue = ukrainianValue;
    }

    public static SemesterType getByNumber(int number) {
        for (SemesterType type: SemesterType.values()) {
            if (type.ordinal() == number - 1) {
                return type;
            }
        }

        return null;
    }

    @JsonValue
    public String getUkrainianValue() {
        return ukrainianValue;
    }

    public int getNumber() {
        return this.ordinal() + 1;
    }

    @Override
    public String toString() {
        return Integer.toString(getNumber());
    }
}
