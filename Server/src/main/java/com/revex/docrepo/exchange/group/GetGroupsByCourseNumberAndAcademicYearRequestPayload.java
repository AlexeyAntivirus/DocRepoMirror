package com.revex.docrepo.exchange.group;

import lombok.Data;

@Data
public class GetGroupsByCourseNumberAndAcademicYearRequestPayload {
	private String groupNamePart;
	private int courseNumber;
	private int beginYear;
	private int endYear;
	private int semester;
}
