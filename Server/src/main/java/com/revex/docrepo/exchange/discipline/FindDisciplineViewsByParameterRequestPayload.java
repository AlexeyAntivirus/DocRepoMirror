package com.revex.docrepo.exchange.discipline;

import com.revex.docrepo.database.utils.EntityDataTransformation;
import lombok.Data;

@Data
public class FindDisciplineViewsByParameterRequestPayload {
	private String parameterKey;
	private String parameterValue;

	public Object getParameterValue() {
		return EntityDataTransformation.transformParameterValue(parameterValue);
	}
}
