package ru.skypro.homework.model;

import lombok.*;


import javax.persistence.Entity;
@Getter
@Setter
@ToString
@Entity
public class CreateOrUpdateComment {
    private String textComment;
}
