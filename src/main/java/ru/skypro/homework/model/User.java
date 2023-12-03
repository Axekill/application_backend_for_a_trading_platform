package ru.skypro.homework.model;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    //логин
    private String userName;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "image")
    private Image image;

    @Size(min = 5, max = 30)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Ad> adList;

    @OneToMany(mappedBy = "author")
    private List<Comment> commentList;
}
