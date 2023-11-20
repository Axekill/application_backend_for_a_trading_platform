package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id",source = "user.id")
    @Mapping(target = "email",source = "user.email")
    @Mapping(target = "firstName",source = "user.firstName")
    @Mapping(target = "lastName",source = "user.lastName")
    @Mapping(target = "phone",source = "user.phone")
    @Mapping(target = "role",source = "user.role")
    @Mapping(target = "image",source = "user.image")
    UserDTO toDTO(User user);


    @Mapping(target = "id",source = "dto.id")
    @Mapping(target = "email",source = "dto.email")
    @Mapping(target = "firstName",source = "dto.firstName")
    @Mapping(target = "lastName",source = "dto.lastName")
    @Mapping(target = "phone",source = "dto.phone")
    @Mapping(target = "role",source = "dto.role")
    @Mapping(target = "image",source = "dto.image")
    User toEntity(UserDTO dto);
}
