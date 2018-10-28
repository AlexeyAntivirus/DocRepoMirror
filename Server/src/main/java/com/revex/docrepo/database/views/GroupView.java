package com.revex.docrepo.database.views;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GroupView {
	private long id;
	private String groupName;

	@Builder
	private GroupView(long id, String groupName) {
		this.groupName = groupName;
		this.id = id;
	}
}
