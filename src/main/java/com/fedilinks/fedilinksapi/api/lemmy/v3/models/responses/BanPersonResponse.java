package com.fedilinks.fedilinksapi.api.lemmy.v3.models.responses;

import com.fedilinks.fedilinksapi.api.lemmy.v3.models.views.PersonView;
import lombok.Builder;

@Builder
public record BanPersonResponse(
        PersonView person_view,
        boolean banned
) {
}