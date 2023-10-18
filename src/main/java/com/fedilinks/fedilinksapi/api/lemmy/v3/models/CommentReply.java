package com.fedilinks.fedilinksapi.api.lemmy.v3.models;

import lombok.Builder;

@Builder
public record CommentReply(
        Long id,
        Long recipient_id,
        Long comment_id,
        boolean read,
        String published
) {
}