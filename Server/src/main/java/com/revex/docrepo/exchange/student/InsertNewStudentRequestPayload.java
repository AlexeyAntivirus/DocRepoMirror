package com.revex.docrepo.exchange.student;

import com.revex.docrepo.database.utils.SemesterType;
import com.revex.docrepo.database.views.GroupView;
import lombok.Data;

@Data
public class InsertNewStudentRequestPayload {
	private String fullName;
	private int beginYear;
	private int endYear;
	private SemesterType semesterType;
	private GroupView group;
	private boolean isExtramural;
	private boolean isShortened;
}
