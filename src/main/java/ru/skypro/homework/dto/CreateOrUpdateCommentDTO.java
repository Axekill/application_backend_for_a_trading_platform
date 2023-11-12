package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description ="Create or update comments" )
public class CreateOrUpdateCommentDTO {
    @Schema(description = "текст")
    @Size(min = 4, max = 60)
    private String  text;
}
