package com.revex.docrepo.database.views;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DisciplineView {
	private long id;
	private String shortName;

	@Builder
	private DisciplineView(long id, String shortName) {
		this.id = id;
		this.shortName = shortName;
	}
}
