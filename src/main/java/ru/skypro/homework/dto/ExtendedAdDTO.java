package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Extend ad")
public class ExtendedAdDTO {
    @Schema(description = "id объявления")
    private long pkId;

    @Schema(description = "имя автора")
    private String authorFirstName;

    @Schema(description = "фамилия автора")
    private String authorLastName;

    @Schema(description = "описание объявления")
    private String description;

    @Schema(description = "emailLogin автора ")
    private String email;

    @Schema(description = "фото объявления")
    private String image;

    @Schema(description = "телефон автора ")
    private String phone;

    @Schema(description = "цена ")
    private int price;

    @Schema(description = "заголовок объявления")
    private String title;


}

