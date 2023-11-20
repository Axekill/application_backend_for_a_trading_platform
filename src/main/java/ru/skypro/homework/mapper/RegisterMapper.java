package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.User;

@Mapper
public interface RegisterMapper {
     RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);
    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "phone", target = "user.phone")
    @Mapping(source = "role", target = "user.role")
    User toModel(RegisterDTO registerDto);
}


