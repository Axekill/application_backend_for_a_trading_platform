package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO {

    @Schema(description = "логин")
    @Size(min = 5, max = 30)
    private String username;
    @Schema(description = "пароль")
    @Size(min = 5, max = 30)
    private String password;
    @Schema(description = "имя")
    @Size(min = 2, max = 20)
    private String firstName;
    @Schema(description = "фамилия")
    @Size(min = 2, max = 20)
    private String lastName;
    @Schema(description = "номер телефона")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    @Schema(description = "роль")
    private Role role;


}

