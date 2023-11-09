package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@SpringMapperConfig
public interface CommentMapper {

    CommentDTO toDTO(Comment comment);

    Comment toEntity(CommentDTO commentDTO);
}
