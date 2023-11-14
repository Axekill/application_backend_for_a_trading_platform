package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper
public interface ExtendedAdMapper {

    ExtendedAdMapper INSTANCE = Mappers.getMapper(ExtendedAdMapper.class);
    @Mapping(source = "ad.pkId", target = "pkId")
    @Mapping(source = "user.authorFirstName", target = "authorFirstName")
    @Mapping(source = "user.authorLastName", target = "authorLastName")
    @Mapping(source = "ad.description", target = "description")
    @Mapping(source = "user.authorEmail", target = "email")
    @Mapping(source = "ad.image", target = "image")
    @Mapping(source = "user.authorPhone", target = "phone")
    @Mapping(source = "ad.price", target = "prise")
    @Mapping(source = "ad.title", target = "title")
    ExtendedAdDTO toDTO(Ad ad, User user);
}
