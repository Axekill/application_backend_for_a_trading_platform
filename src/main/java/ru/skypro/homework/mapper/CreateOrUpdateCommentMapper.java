package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.CreateOrUpdateComment;
@SpringMapperConfig
public interface CreateOrUpdateCommentMapper {

    CreateOrUpdateCommentDTO toDTO(CreateOrUpdateComment createOrUpdateComment);

    CreateOrUpdateComment toEntity(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
