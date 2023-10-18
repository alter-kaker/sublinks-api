package com.fedilinks.fedilinksapi.api.lemmy.v3.models.responses;

import com.fedilinks.fedilinksapi.api.lemmy.v3.models.views.RegistrationApplicationView;
import lombok.Builder;

import java.util.List;

@Builder
public record ListRegistrationApplicationsResponse(
        List<RegistrationApplicationView> registration_applications
) {
}