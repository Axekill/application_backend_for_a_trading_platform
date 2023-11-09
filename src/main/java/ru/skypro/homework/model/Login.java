package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "login")
public class Login {

    @Length(min =4 ,max =32 )
    private String username;

    @Length(min =8 ,max =16 )
    private String password;
}
