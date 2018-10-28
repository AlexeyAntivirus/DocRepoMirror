package com.revex.docrepo.database.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QualificationWorkType {
	DIPLOMA_WORK(1, "Дипломна"),
	COURSE_WORK(0, "Курсова");

	private final int number;
	private final String ukrainianValue;

	QualificationWorkType(int number, String ukrainianValue) {
		this.number = number;
		this.ukrainianValue = ukrainianValue;
	}

	public static QualificationWorkType getByNumber(int number) {
		for (QualificationWorkType type : QualificationWorkType.values()) {
			if (type.getNumber() == number) {
				return type;
			}
		}

		return null;
	}

	public int getNumber() {
		return number;
	}

	@JsonValue
	public String getUkrainianValue() {
		return ukrainianValue;
	}

	@Override
	public String toString() {
		return Integer.toString(getNumber());
	}
}
