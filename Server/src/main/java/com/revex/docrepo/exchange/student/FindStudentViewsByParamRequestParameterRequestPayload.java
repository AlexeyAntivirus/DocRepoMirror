package com.revex.docrepo.exchange.student;

import com.revex.docrepo.database.utils.EntityDataTransformation;
import lombok.Data;

@Data
public class FindStudentViewsByParamRequestParameterRequestPayload {
	private String parameterKey;
	private String parameterValue;

	public Object getParameterValue() {
		return EntityDataTransformation.transformParameterValue(parameterValue);
	}
}
