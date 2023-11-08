package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String firstName;
    /* minLength: 3
       maxLength: 10*/
    private String lastName;
    /* minLength: 3
       maxLength: 10*/

    private String phone;  //pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}
}
