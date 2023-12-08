package ru.skypro.homework.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private String textComment;

    @ManyToOne
    @JoinColumn(name = "author")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "ad")
    private Ad ad;
}
