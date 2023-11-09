package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Comments;

@SpringMapperConfig
public interface CommentsMapper {
    CommentsDTO toDTO(Comments comments);

    Comments toEntity(CommentsDTO commentsDTO);
}
