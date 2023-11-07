package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
@Getter
@Setter
@ToString
@Entity
public class CreateOrUpdateAd {
    private String title;
    private int price;
    private  String description;
}
