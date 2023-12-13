package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.model.Image;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UsersDTO {

    private Long id;
    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String email;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    private Role role;
    private Image image;
}