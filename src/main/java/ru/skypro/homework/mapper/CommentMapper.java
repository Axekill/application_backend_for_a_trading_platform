package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsersMapper.class})
public interface CommentMapper {

    @Mapping(source = "users.id", target = "authorId")
    @Mapping(source = "users.image", target = "authorImage", qualifiedByName = "imageToString")
    @Mapping(source = "users.firstName", target = "authorFirstName")
    @Mapping(source = "comment.id", target = "id")
    @Mapping(source = "comment.textComment", target = "text")
    CommentDTO toDTO(Comment comment, Users users);


    /*@Mapping(target = "users.id", source = "authorId")
    @Mapping(target = "users.image", source = "authorImage")
    @Mapping(target = "users.firstName", source = "authorFirstName")
    @Mapping(source = "text", target = "textComment")
    @Mapping(target = "ad", ignore = true)
    Comment toEntity(CommentDTO dto);*/

    @Mapping(source = "size", target = "count")
    @Mapping(source = "commentList", target = "results")
    CommentsDTO commentsListToComments(Integer size, List<Comment> commentList);

    /*default String image(Image image) {
        return String.valueOf(image.getId());
    }

    default Image imageToString(String authorImage) {
        return new Image();
    }
*/

    @Named("imageToString")
    default String imageToPathString(Image image) {
        return image != null ? ("/users/image/" + image.getId()) : null;

    }
}
