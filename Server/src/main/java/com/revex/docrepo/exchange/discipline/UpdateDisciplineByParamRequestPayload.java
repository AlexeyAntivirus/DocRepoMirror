package com.revex.docrepo.exchange.discipline;

import lombok.Data;

@Data
public class UpdateDisciplineByParamRequestPayload {
	private long id;
	private String parameterKey;
	private Object parameterValue;
}
