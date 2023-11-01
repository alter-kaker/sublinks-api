package com.sublinks.sublinksapi.api.lemmy.v3.post.controllers;

import com.sublinks.sublinksapi.api.lemmy.v3.authentication.JwtPerson;
import com.sublinks.sublinksapi.api.lemmy.v3.community.services.LemmyCommunityService;
import com.sublinks.sublinksapi.api.lemmy.v3.post.mappers.CreatePostMapper;
import com.sublinks.sublinksapi.api.lemmy.v3.post.models.CreatePost;
import com.sublinks.sublinksapi.api.lemmy.v3.post.models.PostResponse;
import com.sublinks.sublinksapi.api.lemmy.v3.post.services.LemmyPostService;
import com.sublinks.sublinksapi.authorization.AuthorizationService;
import com.sublinks.sublinksapi.authorization.enums.AuthorizeAction;
import com.sublinks.sublinksapi.authorization.enums.AuthorizedEntityType;
import com.sublinks.sublinksapi.community.dto.Community;
import com.sublinks.sublinksapi.community.repositories.CommunityRepository;
import com.sublinks.sublinksapi.instance.models.LocalInstanceContext;
import com.sublinks.sublinksapi.language.repositories.LanguageRepository;
import com.sublinks.sublinksapi.person.dto.Person;
import com.sublinks.sublinksapi.post.dto.Post;
import com.sublinks.sublinksapi.post.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping(path = "/api/v3/post")
public class PostOwnerController {
    private final LocalInstanceContext localInstanceContext;
    private final AuthorizationService authorizationService;
    private final LemmyCommunityService lemmyCommunityService;
    private final LemmyPostService lemmyPostService;
    private final PostService postService;
    private final LanguageRepository languageRepository;
    private final CommunityRepository communityRepository;
    private final CreatePostMapper createPostMapper;

    @PostMapping
    @Transactional
    public PostResponse create(@Valid @RequestBody final CreatePost createPostForm, JwtPerson principal) {

        final Community community = communityRepository.findById((long) createPostForm.community_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Person person = null;
        if (principal != null) {
            person = (Person) principal.getPrincipal();
        }
        authorizationService
                .canPerson(person)
                .performTheAction(AuthorizeAction.create)
                .onEntity(AuthorizedEntityType.post)
                .defaultResponse(community.isPostingRestrictedToMods() ? AuthorizationService.ResponseType.decline : AuthorizationService.ResponseType.allow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Post post = createPostMapper.map(
                createPostForm,
                localInstanceContext.instance(),
                community,
                languageRepository.findById((long) createPostForm.language_id()).get(),
                true
        );

        postService.createPost(post, community, person);

        return PostResponse.builder()
                .post_view(lemmyPostService.postViewFromPost(post, person))
                .build();
    }

    @PutMapping
    PostResponse update() {

        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("delete")
    PostResponse delete() {

        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}