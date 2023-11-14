package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Comment;

@Mapper
public interface CommentsMapper {
    CommentsDTO toDTO(Comment comment);

    Comment toEntity(CommentsDTO commentsDTO);
}
