package com.revex.docrepo.database.views;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherView {
	private long id;
	private String fullName;

	@Builder
	public TeacherView(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
}
