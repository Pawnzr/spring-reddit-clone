package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.CommentsDto;
import com.example.springredditclone.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.springredditclone.model.Comment;
import com.example.springredditclone.model.User;
import com.github.marlonlom.utilities.timeago.TimeAgo;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    public abstract Comment map(CommentsDto commentDto, Post post, User user);


    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    @Mapping(target = "duration", expression = "java(getDuration(comment))")
    public abstract CommentsDto mapToDto(Comment comment);

    String getDuration(Comment comment) {
        return TimeAgo.using(comment.getCreatedDate().toEpochMilli());
    }
}