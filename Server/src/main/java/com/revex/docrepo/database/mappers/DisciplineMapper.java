package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.entities.Discipline;
import com.revex.docrepo.database.utils.WorkType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DisciplineMapper implements RowMapper<Discipline> {
    @Override
    public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Discipline.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("nazva"))
                .shortName(rs.getString("skornazva"))
                .semesterNumber(rs.getInt("sem"))
                .workType(WorkType.getByUkrainianValue(rs.getString("vid")))
                .build();
    }
}
