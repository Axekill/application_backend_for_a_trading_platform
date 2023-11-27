package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Data
@Schema(description = "Comments")
public class CommentsDTO {

    @Schema(description = "Счётчик показывающий кол-во коммент")
    private int count;

    @Schema(description = "лист комментариев")
    private CommentDTO result;
}
