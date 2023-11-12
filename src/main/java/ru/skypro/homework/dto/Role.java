package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Какие бывают роли")
public enum Role {
    USER, ADMIN
}
