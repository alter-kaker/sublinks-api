package com.fedilinks.fedilinksapi.api.lemmy.v3.models.requests;

import lombok.Builder;

@Builder
public record ResolvePostReport(
        int report_id,
        boolean resolved,
        String auth
) {
}