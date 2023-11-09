package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
public class CreateOrUpdateAd {
    @Length(min = 4, max = 32)
    private String title;

    @Min(value = 0)
    @Max(value = 10000000)
    private int price;

    @Length(min = 8, max = 64)
    private String description;
}
