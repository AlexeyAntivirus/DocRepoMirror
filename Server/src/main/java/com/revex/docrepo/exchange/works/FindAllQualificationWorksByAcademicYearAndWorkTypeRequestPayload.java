package com.revex.docrepo.exchange.works;

import com.revex.docrepo.database.utils.QualificationWorkType;
import com.revex.docrepo.database.utils.WorkType;
import lombok.Data;

@Data
public class FindAllQualificationWorksByAcademicYearAndWorkTypeRequestPayload {
	private int beginYear;
	private int endYear;
	private QualificationWorkType workType;
}
