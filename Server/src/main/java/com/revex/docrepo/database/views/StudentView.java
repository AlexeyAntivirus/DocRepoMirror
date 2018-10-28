package com.revex.docrepo.database.views;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StudentView {
	private long id;
	private String fullName;

	@Builder
	public StudentView(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
}
