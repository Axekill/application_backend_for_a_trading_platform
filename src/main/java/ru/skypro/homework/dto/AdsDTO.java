package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

@Data
public class AdsDTO {
    private int count;
    private Ad result;

}
