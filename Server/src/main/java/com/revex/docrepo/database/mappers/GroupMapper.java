package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.entities.Group;
import com.revex.docrepo.database.utils.SemesterType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class GroupMapper implements RowMapper<Group> {

	@Override
	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Group.builder()
				.id(rs.getLong("id"))
				.name(rs.getString("nazva"))
				.courseNumber(rs.getInt("kurs"))
				.faculty(rs.getString("fakult"))
				.specialty(rs.getString("spec"))
				.branch(rs.getString("galuz"))
				.educationLevel(rs.getString("okr"))
				.educationProgram(rs.getString("op"))
				.beginYear(rs.getInt("rik1"))
				.endYear(rs.getInt("rik2"))
				.isShortened(rs.getInt("skor") == 1)
				.semesterType(SemesterType.getByNumber(rs.getInt("sem")))
				.isExtramural(rs.getInt("zao") == 1)
				.build();
	}
}
