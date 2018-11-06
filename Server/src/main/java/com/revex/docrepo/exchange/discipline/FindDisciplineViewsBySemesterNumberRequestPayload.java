package com.revex.docrepo.exchange.discipline;

import lombok.Data;

@Data
public class FindDisciplineViewsBySemesterNumberRequestPayload {
	private int semesterNumber;
	private String part;
}
