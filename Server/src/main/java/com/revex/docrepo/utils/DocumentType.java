package com.revex.docrepo.utils;

import com.revex.docrepo.database.utils.QualificationWorkType;

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

    public static DocumentType fromQualificationWork(QualificationWorkType type) {
		for (DocumentType documentType: DocumentType.values()) {
			if (type.ordinal() == documentType.ordinal()) {
				return documentType;
			}
		}

		return null;
    }
}
