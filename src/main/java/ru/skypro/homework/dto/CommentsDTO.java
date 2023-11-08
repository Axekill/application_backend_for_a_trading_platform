package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Comment;

@Data
public class CommentsDTO {
    private int count;
    private Comment result;
}
