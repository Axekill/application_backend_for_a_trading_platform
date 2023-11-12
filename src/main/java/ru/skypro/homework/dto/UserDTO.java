package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Schema(description = "User")
public class UserDTO {

    @Schema(description = "id ")
    private int id;

    @Schema(description = "логин ")
    private String email;

    @Schema(description = "имя ")
    private String firstName;

    @Schema(description = "фамилия ")
    private String lastName;

    @Schema(description = "номер телефона ")
    private String phone;

    @Schema(description = "роль ")
    private Role role;

    @Schema(description = "фото пользователя")
    private String image;
}