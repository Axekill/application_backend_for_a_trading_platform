package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.skypro.homework.dto.Role;

import javax.persistence.Entity;
@Getter
@Setter
@ToString
@Entity
public class Register {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
