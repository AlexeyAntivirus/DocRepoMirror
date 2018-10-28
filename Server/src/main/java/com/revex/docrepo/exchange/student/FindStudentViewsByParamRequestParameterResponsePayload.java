package com.revex.docrepo.exchange.student;

import com.revex.docrepo.database.views.StudentView;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindStudentViewsByParamRequestParameterResponsePayload {
	private List<StudentView> students;
}
