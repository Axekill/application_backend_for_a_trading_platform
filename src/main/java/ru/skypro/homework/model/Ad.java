package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
@Data
@Entity
public class Ad {
    private long authorId;
    private String image;
    private long pkId;
    private int price;
    private String title;



}
