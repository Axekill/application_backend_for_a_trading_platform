package ru.skypro.homework.model;

import lombok.*;


import javax.persistence.Entity;
@Data
@Entity
public class CreateOrUpdateComment {
    private String textComment;
}
