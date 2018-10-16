package com.revex.docrepo.exchange.group;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteGroupByIdResponsePayload {
    private boolean isSuccessful;
}
