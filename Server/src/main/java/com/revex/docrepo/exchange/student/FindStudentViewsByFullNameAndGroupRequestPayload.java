package com.revex.docrepo.exchange.student;

import lombok.Data;

@Data
public class FindStudentViewsByFullNameAndGroupRequestPayload {
	private long groupId;
	private String studentFullNamePart;
	private int beginYear;
	private int endYear;
	private int semesterNumber;
}
