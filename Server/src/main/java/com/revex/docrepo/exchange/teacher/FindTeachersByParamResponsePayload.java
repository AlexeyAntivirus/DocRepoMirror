package com.revex.docrepo.exchange.teacher;

import com.revex.docrepo.database.entities.Teacher;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindTeachersByParamResponsePayload {
    private List<Teacher> teachers;
}
