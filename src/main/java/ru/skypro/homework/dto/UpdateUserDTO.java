package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Update data user ")
public class UpdateUserDTO {
    @Schema(description = "имя ")

    @Size(min = 2, max = 20)
    private String firstName;

    @Schema(description = "фамилия ")
    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @Schema(description = "номер телефона ")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
