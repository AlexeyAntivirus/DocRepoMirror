package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.entities.Teacher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeacherMapper implements RowMapper<Teacher> {
	@Override
	public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Teacher.builder()
				.id(rs.getLong("id"))
				.fullName(rs.getString("pib"))
				.cathedra(rs.getString("kaf"))
				.degree(rs.getString("stup"))
				.position(rs.getString("posada"))
				.rank(rs.getString("zvan"))
				.isWorking(rs.getInt("diuchi") == 1)
				.build();
	}
}
