package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Data
public class AdsDTO {

    private List<AdDTO> result;

    private int count;

}
