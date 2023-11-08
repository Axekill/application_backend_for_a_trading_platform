package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
@Data
@Entity
public class Ads {
    private int count;
    private Ad result;

}
