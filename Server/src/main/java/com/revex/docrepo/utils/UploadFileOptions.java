package com.revex.docrepo.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadFileOptions {
    private DocumentType documentType;
    private String educationLevel;
    private String studyYear;
    private String disciplineName;
    private String groupName;
    private String studentFullName;

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder()
                .append(documentType);

        if (documentType == DocumentType.DIPLOMA_WORKS) {
            resultString.append("/").append(studyYear)
                    .append("/").append(educationLevel)
                    .append("/").append(groupName)
                    .append("/").append(studentFullName);
        } else if (documentType == DocumentType.COURSE_WORKS) {
            resultString.append("/").append(studyYear)
                    .append("/").append(disciplineName)
                    .append("/").append(groupName)
                    .append("/").append(studentFullName);
        }

        return resultString.toString();
    }
}
