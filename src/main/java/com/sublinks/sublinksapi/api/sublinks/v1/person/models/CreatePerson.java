package com.sublinks.sublinksapi.api.sublinks.v1.person.models;

import com.sublinks.sublinksapi.api.sublinks.v1.languages.models.LanguageResponse;
import java.util.List;
import java.util.Optional;
import lombok.Builder;

@Builder
public record CreatePerson(String name,
                           String displayName,
                           Optional<String> email,
                           List<LanguageResponse> languages,
                           Optional<String> avatarImageUrl,
                           Optional<String> bannerImageUrl,
                           Optional<String> bio,
                           Optional<String> matrixUserId,
                           Optional<String> password,
                           Optional<String> passwordConfirmation,
                           Optional<String> answer,
                           Optional<String> captcha_token,
                           Optional<String> captcha_answer) {

}