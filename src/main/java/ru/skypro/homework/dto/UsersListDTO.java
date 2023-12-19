package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersListDTO {
    private int count;
    private List<UsersDTO> results;
}
