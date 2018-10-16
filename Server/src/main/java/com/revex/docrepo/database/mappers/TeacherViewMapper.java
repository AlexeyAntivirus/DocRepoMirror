package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.views.TeacherView;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeacherViewMapper implements RowMapper<TeacherView> {
    @Override
    public TeacherView mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TeacherView.builder()
                .id(rs.getLong("id"))
                .fullName(rs.getString("pib"))
                .build();
    }
}
