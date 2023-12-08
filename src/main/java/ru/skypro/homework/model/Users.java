package ru.skypro.homework.model;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //логин
//    @Column(name = "user_name")
//    private String username;

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

    @OneToMany(mappedBy = "users")
    private List<Ad> adList;

    @OneToMany(mappedBy = "users")
    private List<Comment> commentList;
}
