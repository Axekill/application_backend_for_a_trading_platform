package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import ru.skypro.homework.dto.Role;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "register")
public class Register {

    @Length(min=4,max =32)
    private String username;

    @Length(min=8,max =16)
    private String password;

    @Length(min=2,max =16)
    private String firstName;

    @Length(min=2,max =16)
    private String lastName;

    @Pattern(regexp = "\\+7\s?\\(?\\d{3}\\)?\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    private Role role;
}
