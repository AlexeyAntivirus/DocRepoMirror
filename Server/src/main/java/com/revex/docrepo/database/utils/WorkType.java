package com.revex.docrepo.database.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WorkType {
    COURSE_WORK("курсова робота"),
    COURSE_PROJECT("курсовий проект");

    private final String ukrainianValue;

    WorkType(String ukrainianValue) {
        this.ukrainianValue = ukrainianValue;
    }

    @JsonValue
    public String getUkrainianValue() {
        return ukrainianValue;
    }

    @Override
    public String toString() {
        return ukrainianValue;
    }

    public static WorkType getByUkrainianValue(String ukrainianValue) {
        for (WorkType workType: WorkType.values()) {
            if (workType.getUkrainianValue().equals(ukrainianValue)) {
                return workType;
            }
        }

        return null;
    }
}
