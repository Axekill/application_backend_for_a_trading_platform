package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Data
@Schema(description ="Create or update ad")
public class CreateOrUpdateAdDTO {



    @Size(min = 6, max = 20)
    private String  title;

    @Schema(description = "Цена")
    @Size(max = 9999999)
    private int price;

    @Schema(description = "Описание объявления")
    @Size(min = 1, max = 100)
    private String  description;
}
