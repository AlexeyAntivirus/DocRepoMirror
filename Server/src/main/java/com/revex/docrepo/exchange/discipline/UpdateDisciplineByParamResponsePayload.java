package com.revex.docrepo.exchange.discipline;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDisciplineByParamResponsePayload {
    private boolean isSuccessful;
}
