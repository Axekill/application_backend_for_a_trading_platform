package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Ad")
public class AdDTO {
    @Schema(description = "id ad")
    private Long id;

    @Schema(description = "id автора")
    private Long author;

    @Schema(description = "фото в объявлении")
    private String image;

    @Schema(description = "цена")
    private int price;

    @Schema(description = "заголовок объявления")
    private String title;

    private String description;

}
