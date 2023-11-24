package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

@Mapper(uses = {CommentMapper.class},componentModel = "spring")
public interface CommentsMapper {

    @Mapping(source = "dto.commentList", target = "result")
    CommentsDTO toDTO(CommentDTO dto);
}
