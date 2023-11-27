package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Data
@Schema(description = "множество AD")
public class AdsDTO {

    @Schema(description = "Лист объявлений")
    private List<Ad> result;

    @Schema(description = "счётчик объявлений")
    private int count;

}
