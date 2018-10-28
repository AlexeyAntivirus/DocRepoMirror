package com.revex.docrepo.exchange.student;

import com.revex.docrepo.database.utils.SemesterType;
import lombok.Data;

@Data
public class InsertNewStudentRequestPayload {
	private String fullName;
	private int beginYear;
	private int endYear;
	private SemesterType semesterType;
	private long groupId;
}
