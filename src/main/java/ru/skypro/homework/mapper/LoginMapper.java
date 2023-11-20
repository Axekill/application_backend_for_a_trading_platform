package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.model.User;

@Mapper
public interface LoginMapper {
 LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
    @Mapping(source = "dto.username",target = "username")
    @Mapping(source = "dto.password",target = "password" )
    User toEntity(LoginDTO dto);

    @Mapping(source = "user.username",target = "username")
    @Mapping(source = "user.password",target = "password" )
    LoginDTO toDTO(User user);
}
