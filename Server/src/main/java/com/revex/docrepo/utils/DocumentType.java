package com.revex.docrepo.utils;

public enum DocumentType {
    DIPLOMA_WORKS("Дипломні"),
    COURSE_WORKS("Курсові"),
    ORDERS("Накази"),
    PROTOCOLS("Протоколи"),
    REPORTS("Звіти");

    private final String dir;

    DocumentType(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return dir;
    }
}
