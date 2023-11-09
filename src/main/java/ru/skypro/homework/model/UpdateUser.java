package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class UpdateUser {

    @Length(min = 3, max = 10)
    private String firstName;

    @Length(min = 3, max = 10)
    private String lastName;

    @Pattern(regexp = "\\+7\s?\\(?\\d{3}\\)?\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
