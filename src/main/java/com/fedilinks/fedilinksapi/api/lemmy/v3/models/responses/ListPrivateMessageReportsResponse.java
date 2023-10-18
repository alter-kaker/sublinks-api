package com.fedilinks.fedilinksapi.api.lemmy.v3.models.responses;

import com.fedilinks.fedilinksapi.api.lemmy.v3.models.views.PrivateMessageReportView;
import lombok.Builder;

import java.util.List;

@Builder
public record ListPrivateMessageReportsResponse(
        List<PrivateMessageReportView> private_message_reports
) {
}