package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
@Data
@Entity
public class CreateOrUpdateAd {
    private String title;
    private int price;
    private  String description;
}
