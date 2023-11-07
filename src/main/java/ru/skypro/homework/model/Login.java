package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
@Getter
@Setter
@ToString
@Entity
public class Login {
    private String username;
    private String password;
}
