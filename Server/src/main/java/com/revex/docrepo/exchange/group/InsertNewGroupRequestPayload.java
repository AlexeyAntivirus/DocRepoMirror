package com.revex.docrepo.exchange.group;

import com.revex.docrepo.database.utils.SemesterType;
import lombok.Builder;
import lombok.Data;

@Data
public class InsertNewGroupRequestPayload {
    private String name;
    private int courseNumber;
    private String faculty;
    private String specialty;
    private String branch;
    private String educationLevel;
    private String educationProgram;
    private int beginYear;
    private int endYear;
    private SemesterType semesterType;
    private boolean isExtramural;
    private boolean isShortened;
}
