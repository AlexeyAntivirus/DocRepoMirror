package com.revex.docrepo.exchange.group;

import com.revex.docrepo.database.utils.EntityDataTransformation;
import lombok.Data;

@Data
public class UpdateGroupByParameterRequestPayload {
    private long id;
    private String parameterKey;
    private Object parameterValue;

    public Object getParameterValue() {
        return EntityDataTransformation.transformParameterValue(parameterValue);
    }
}
