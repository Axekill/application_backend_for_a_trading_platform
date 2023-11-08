package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDTO {
    private long authorId;
    private String image;
    private long pkId;
    private int price;
    private String title;
}
