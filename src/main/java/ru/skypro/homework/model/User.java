package ru.skypro.homework.model;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String userImage;
    private String userName;
    private String password;
    @Transient
    private List<Ad> adList;
    private int countAd;


}
