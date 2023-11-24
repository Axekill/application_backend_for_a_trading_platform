package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface ExtendedAdMapper {


    @Mapping(source = "ad.id", target = "id")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "ad.description", target = "description")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "ad.image", target = "image")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "ad.price", target = "prise")
    @Mapping(source = "ad.title", target = "title")
    ExtendedAdDTO toDTO(Ad ad, User user);

    @Mapping(target = "firstName", source = "dto.authorFirstName")
    @Mapping(target = "lastName", source = "dto.authorLastName")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "phone", source = "dto.phone")
    User toEntity(ExtendedAdDTO dto);

    Ad adToEntity(ExtendedAdDTO dto);


}