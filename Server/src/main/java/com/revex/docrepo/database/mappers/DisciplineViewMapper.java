package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.views.DisciplineView;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DisciplineViewMapper implements RowMapper<DisciplineView> {
    @Override
    public DisciplineView mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DisciplineView.builder()
                .id(rs.getLong("id"))
                .shortName(rs.getString("skornazva"))
                .build();
    }
}
