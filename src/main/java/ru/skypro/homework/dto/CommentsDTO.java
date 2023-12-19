package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Data
public class CommentsDTO {

    private int count;

    private List<CommentDTO> results;
}
