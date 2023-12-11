package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {

    @Mapping(source = "users.id", target = "authorId")
    @Mapping(source = "users.image", target = "authorImage")
    @Mapping(source = "users.firstName", target = "authorFirstName")
    CommentDTO toDTO(Comment comment,Users users);


    @Mapping(target = "users.id", source = "dto.authorId")
    @Mapping(target = "users.image", source = "dto.authorImage")
    @Mapping(target = "users.firstName", source = "dto.authorFirstName")
    Comment toEntity(CommentDTO dto);

    default String image(Image image) {
        return String.valueOf(image.getId());
    }

    default Image imageToString(String authorImage){
        return new Image();
    }
    ;


}
