package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDTO {

    @Size(min = 4, max = 32)
    private String  title;

    @Size(min = 0 ,max = 10000000)
    private int price;

    @Size(min = 8, max = 64)
    private String  description;
}
