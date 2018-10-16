package com.revex.docrepo.exchange.teacher;

import com.revex.docrepo.database.entities.Teacher;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllTeachersResponsePayload {
    private List<Teacher> teachers;
}
