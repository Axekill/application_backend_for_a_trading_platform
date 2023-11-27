package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface ExtendedAdMapper {


    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    ExtendedAdDTO toDTO(Ad ad);

   @Mapping(target = "firstName", source = "authorFirstName")
    @Mapping(target = "lastName", source = "authorLastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    User toEntity(ExtendedAdDTO dto);

    Ad adToEntity(ExtendedAdDTO dto);


}