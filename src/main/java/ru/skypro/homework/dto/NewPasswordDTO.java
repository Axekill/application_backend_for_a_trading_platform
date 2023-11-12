package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@Schema(description = "Новый пароль")
public class NewPasswordDTO {
    @Schema(description = "текущий пароль")
    @Size(min = 5, max = 30)
    private String currentPassword;
    @Schema(description = "новый пароль")
    @Size(min = 5, max = 30)
    private String newPassword;

}