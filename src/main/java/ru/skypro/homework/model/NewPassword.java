package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
@Data
@Entity
public class NewPassword {
    @Length(min=8,max =16 )
    private String currentPassword ;

    @Length(min=8,max =16 )
    private String newPassword;
}
