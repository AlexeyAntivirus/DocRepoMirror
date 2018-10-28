package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.views.StudentView;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentViewMapper implements RowMapper<StudentView> {
	@Override
	public StudentView mapRow(ResultSet rs, int rowNum) throws SQLException {
		return StudentView.builder()
				.id(rs.getLong("id"))
				.fullName(rs.getString("pib"))
				.build();
	}
}
