package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
@Data
@Entity
public class UpdateUser {
    private String firstName;
    /* minLength: 3
       maxLength: 10*/
    private String lastName;
    /* minLength: 3
       maxLength: 10*/

    private String phone;  //pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}
}
