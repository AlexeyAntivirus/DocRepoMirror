package com.revex.docrepo.database.entities;

import com.revex.docrepo.database.utils.WorkType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Discipline {
    private long id;
    private String name;
    private String shortName;
    private int semesterNumber;
    private WorkType workType;
}
