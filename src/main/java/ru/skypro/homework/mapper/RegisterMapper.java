package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

/*    @Mapping(source = "dto.userName", target = "userName")
    @Mapping(source = "dto.password", target = "password")
    @Mapping(source = "dto.firstName", target = "firstName")
    @Mapping(source = "dto.lastName", target = "lastName")
    @Mapping(source = "dto.phone", target = "phone")
    @Mapping(source = "dto.role", target = "role")*/
    User toEntity(RegisterDTO dto);


   /* @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "user.password", target = "password")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.role", target = "role")*/
    RegisterDTO toDTO(User user);
}


