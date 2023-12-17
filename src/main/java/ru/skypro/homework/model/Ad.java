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

    @OneToOne(mappedBy = "ad",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Image image;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users users;

    @OneToMany(mappedBy = "ad")
    private List<Comment> comments;


}
