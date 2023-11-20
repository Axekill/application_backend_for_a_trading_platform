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


    @Mapping(source = "user.id", target = "authorId")
    @Mapping(source = "user.image", target = "authorImage")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    CommentDTO toDTO(Comment comment, User user);


    @Mapping(target = "id", source = "dto.authorId")
    @Mapping(target = "image", source = "dto.authorImage")
    @Mapping(target = "firstName", source = "dto.authorFirstName")
    User toEntity(CommentDTO dto);


}
