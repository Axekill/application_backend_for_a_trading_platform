package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "User")
public class UserDTO {

    @Schema(description = "id ")
    private int id;

    @Schema(description = "имя ")
    private String firstName;

    @Schema(description = "фамилия ")
    private String lastName;

    @Email
    @NotNull
    @Schema(description = "почта ")
    private String email;

    @Schema(description = "номер телефона ")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "роль ")
    private Role role;

    @Schema(description = "фото пользователя")
    private String image;
}