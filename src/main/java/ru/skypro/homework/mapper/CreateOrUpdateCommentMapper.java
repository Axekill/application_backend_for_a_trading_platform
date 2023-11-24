package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

@Mapper(componentModel = "spring")
public interface CreateOrUpdateCommentMapper {

    @Mapping(source = "dto.text",target = "text")
    Comment toEntity(CreateOrUpdateCommentDTO dto);


    @Mapping(source = "comment.text",target = "text")
    CreateOrUpdateCommentDTO toDTO(Comment comment);
}
