package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface ExtendedAdMapper {


    @Mapping(source = "users.firstName", target = "authorFirstName")
    @Mapping(source = "users.lastName", target = "authorLastName")
    @Mapping(source = "users.email", target = "email")
    @Mapping(source = "users.phone", target = "phone")
    ExtendedAdDTO toDTO(Ad ad);

   @Mapping(target = "firstName", source = "authorFirstName")
    @Mapping(target = "lastName", source = "authorLastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
   Users toEntity(ExtendedAdDTO dto);

    Ad adToEntity(ExtendedAdDTO dto);

    default String image (Image image){
        return String.valueOf(image.getId());
    };
    default Image imageToString(String authorImage){
        return new Image();
    }

}