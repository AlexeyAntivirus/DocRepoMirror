package com.revex.docrepo.exchange.group;

import com.revex.docrepo.database.entities.Group;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetGroupByIdResponsePayload {
	private Group group;
}
