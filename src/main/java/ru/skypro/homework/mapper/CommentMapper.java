package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "comment.createComment", target = "createAt")
    @Mapping(source = "comment.id", target = "id")
    @Mapping(source = "comment.text", target = "text")
    @Mapping(source = "user.authorId", target = "authorId")
    @Mapping(source = "user.authorImage", target = "authorImage")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    CommentDTO toDto(Comment comment, User user);


    @Mapping(source = "author", target = "authorId")
    @Mapping(source = "authorImage", target = "user.authorImage")
    @Mapping(source = "authorFirstName", target = "user.firstName")
    Comment toEntity(CommentDTO commentDTO);





}
