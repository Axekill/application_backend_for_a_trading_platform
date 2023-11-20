package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "регистрация")
public class LoginDTO {

    @Schema(description = "логин")
    @Size(min = 5, max =30)
    private String username;
    @Schema(description = "пароль")
    @Size(min = 5, max = 30)
    private String password;


}
