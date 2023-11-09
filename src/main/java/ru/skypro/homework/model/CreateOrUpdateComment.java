package ru.skypro.homework.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.persistence.Entity;
@Data
@Entity
public class CreateOrUpdateComment {

    @Length(min = 8, max = 64)
    private String textComment;
}
