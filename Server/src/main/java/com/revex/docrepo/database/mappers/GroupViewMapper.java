package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.views.GroupView;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupViewMapper implements RowMapper<GroupView> {

	@Override
	public GroupView mapRow(ResultSet rs, int rowNum) throws SQLException {
		return GroupView.builder()
				.id(rs.getLong("id"))
				.groupName(rs.getString("nazva"))
				.build();
	}
}
