package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@Mapper
public interface CommentMapper {

    CommentDTO toDTO(Comment comment);

    Comment toEntity(CommentDTO commentDTO);
}
