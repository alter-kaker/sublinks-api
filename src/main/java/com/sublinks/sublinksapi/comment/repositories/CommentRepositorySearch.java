package com.sublinks.sublinksapi.comment.repositories;

import com.sublinks.sublinksapi.comment.dto.Comment;
import com.sublinks.sublinksapi.comment.models.CommentSearchCriteria;
import com.sublinks.sublinksapi.person.dto.Person;
import com.sublinks.sublinksapi.community.dto.Community;
import java.util.List;

public interface CommentRepositorySearch {

  List<Comment> allCommentsBySearchCriteria(CommentSearchCriteria commentSearchCriteria);

  List<Comment> allCommentsByPerson(Person person);

  List<Comment> allCommentsByCommunityAndPerson(Community community, Person person);
}
