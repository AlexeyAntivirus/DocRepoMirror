package com.revex.docrepo.database.utils;

public class EntityDataTransformation {

    public static Object transformParameterValue(Object parameterValue) {
        Object actualParameterValue = parameterValue;

        if (actualParameterValue instanceof Boolean) {
            actualParameterValue = Boolean.parseBoolean(actualParameterValue.toString()) ? 1 : 0;
        }

        return actualParameterValue;
    }
}
