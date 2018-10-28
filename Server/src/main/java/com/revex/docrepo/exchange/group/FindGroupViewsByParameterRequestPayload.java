package com.revex.docrepo.exchange.group;

import com.revex.docrepo.database.utils.EntityDataTransformation;
import lombok.Data;

@Data
public class FindGroupViewsByParameterRequestPayload {
	private String parameterKey;
	private String parameterValue;

	public Object getParameterValue() {
		return EntityDataTransformation.transformParameterValue(parameterValue);
	}
}
