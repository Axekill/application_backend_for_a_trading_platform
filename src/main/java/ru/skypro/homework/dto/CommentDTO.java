package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Comments")
public class CommentDTO {
    @Schema(description = "id комментария")
    private int id;

    @Schema(description = "id автора")
    private Long authorId;

    @Schema(description = "аватар автора")
    private String authorImage;

    @Schema(description = "имя автора")
    private String authorFirstName;

    @Schema(description = "дата и время создания комментария")
    private long createdAt;

    @Schema(description = "текст")
    private String text;




}
