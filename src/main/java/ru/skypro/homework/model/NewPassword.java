package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
@Data
@Entity
public class NewPassword {
    private String currentPassword ;
    private String newPassword;
}
