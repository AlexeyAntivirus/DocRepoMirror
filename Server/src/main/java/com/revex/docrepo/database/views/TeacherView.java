package com.revex.docrepo.database.views;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherView {
    private long id;
    private String fullName;
}
