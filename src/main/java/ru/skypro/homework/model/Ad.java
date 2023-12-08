package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int price;
    private String description;

    @OneToOne
    @JoinColumn(name = "image")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "author")
    private Users users;

    @OneToMany
    @JoinColumn(name = "comment")
    private List<Comment> comments;


}
