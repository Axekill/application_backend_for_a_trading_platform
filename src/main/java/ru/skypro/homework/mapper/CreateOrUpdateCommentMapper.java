package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

@SpringMapperConfig
public interface CreateOrUpdateCommentMapper {

    CreateOrUpdateCommentDTO toDTO(CreateOrUpdateComment createOrUpdateComment);

    CreateOrUpdateComment toEntity(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
