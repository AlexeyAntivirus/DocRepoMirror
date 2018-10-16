package com.revex.docrepo.exchange.teacher;

import com.revex.docrepo.database.utils.EntityDataTransformation;
import lombok.Data;

@Data
public class UpdateTeacherByParamRequestPayload {
    private long id;
    private String parameterKey;
    private Object parameterValue;

    public Object getParameterValue() {
        return EntityDataTransformation.transformParameterValue(parameterValue);
    }
}
