package com.revex.docrepo.database.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Teacher {
    private long id;
    private String fullName;
    private String cathedra;
    private String position;
    private String degree;
    private String rank;
    private boolean isWorking;
}
