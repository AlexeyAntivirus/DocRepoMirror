package com.revex.docrepo.exchange.works;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteQualificationWorkByIdResponsePayload {
	private boolean isSuccessful;
}
