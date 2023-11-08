package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long authorId;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Long pkId;
    private String textComment;
}
