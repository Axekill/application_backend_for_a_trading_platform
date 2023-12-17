package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ExtendedAdDTO {
    private long pk;
    private String image;
    @Size(min = 0, max = 10000000)
    private int price;
    @Size(min = 4, max = 32)
    private String title;
    @Size(min = 8, max = 64)
    private String description;
    private String authorFirstName;
    private String authorLastName;
    @Email
    @NotNull
    private String email;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;


}

