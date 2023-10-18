package com.fedilinks.fedilinksapi.api.lemmy.v3.models.views;

import com.fedilinks.fedilinksapi.api.lemmy.v3.models.Person;
import com.fedilinks.fedilinksapi.api.lemmy.v3.models.aggregates.PersonAggregates;
import lombok.Builder;

@Builder
public record PersonView(
        Person person,
        PersonAggregates counts
) {
}