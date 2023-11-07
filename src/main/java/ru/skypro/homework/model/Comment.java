package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@Entity
public class Comment {
    private Long authorId;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Long pkId;
    private String textComment;
}
