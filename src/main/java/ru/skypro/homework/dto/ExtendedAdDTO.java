package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Extend ad")
public class ExtendedAdDTO {
    @Schema(description = "id объявления")
    private long id;

    @Schema(description = "фото объявления")
    private String image;

    @Schema(description = "цена ")
    @Size(min = 0, max = 10000000)
    private int price;

    @Schema(description = "заголовок объявления")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "описание объявления")
    @Size(min = 8, max = 64)
    private String description;

    @Schema(description = "имя автора")
    private String authorFirstName;

    @Schema(description = "фамилия автора")
    private String authorLastName;

    @Email
    @NotNull
    @Schema(description = "email автора ")
    private String email;

    @Schema(description = "телефон автора ")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;


}

