package ru.skypro.homework.mapper;

import io.swagger.v3.oas.annotations.media.Schema;
import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.AdsDTO;

import javax.validation.constraints.NotBlank;

@SpringMapperConfig
@Schema(description = "Объявление")
public class AdsMapper {

   @Schema(description = "id ad")
   private long pkId;
   @Schema(description = "id автора")
   private long author;

    @Schema(description = "фото в объявлении")
    private String image;


    @Schema(description = "цена")
    private int price;

    @Schema(description = "заголовок объявления")
    private String title;


}
