package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.control.MappingControl;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pkId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String image;
    private int price;
    private String title;
    private String description;
    private int counterComment;

    @Transient
    private List<Comment> comments;





}
