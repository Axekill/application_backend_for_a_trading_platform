package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

@Mapper
public interface CreateOrUpdateCommentMapper {

    CreateOrUpdateCommentDTO toDTO(Comment comment);

   Comment toEntity(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
