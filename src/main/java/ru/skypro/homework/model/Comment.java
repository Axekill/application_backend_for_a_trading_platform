package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Comments")
public class Comment {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private long createdAt;

    @Id
    @GeneratedValue
    private long pkId;

    private String textComment;
}
