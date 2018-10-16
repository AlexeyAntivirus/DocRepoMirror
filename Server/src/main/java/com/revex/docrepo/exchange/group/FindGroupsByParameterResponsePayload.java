package com.revex.docrepo.exchange.group;

import com.revex.docrepo.database.entities.Group;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindGroupsByParameterResponsePayload {
    private List<Group> groups;
}
