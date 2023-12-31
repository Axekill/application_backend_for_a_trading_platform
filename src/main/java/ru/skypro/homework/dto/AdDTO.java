package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Image;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {
    private Long pk;
    private Long author;
    private String image;
    private int price;
    private String title;
    private String description;


}