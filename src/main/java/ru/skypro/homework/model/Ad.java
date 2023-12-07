package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String image;

    @ManyToOne
    @JoinColumn(name = "author")
    private User user;

    @OneToMany
    @JoinColumn(name = "comment")
    private List<Comment> comments;


}
