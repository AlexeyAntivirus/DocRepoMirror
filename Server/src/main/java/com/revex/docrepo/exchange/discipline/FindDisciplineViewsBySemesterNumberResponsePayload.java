package com.revex.docrepo.exchange.discipline;

import com.revex.docrepo.database.views.DisciplineView;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindDisciplineViewsBySemesterNumberResponsePayload {
	private List<DisciplineView> disciplines;
}
