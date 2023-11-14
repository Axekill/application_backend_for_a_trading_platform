package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.model.User;

@Mapper
public interface LoginMapper {
 LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
    @Mapping(source = "userName",target = "user.userName")
    @Mapping(source = "password",target = "user.password" )
    User toModel(LoginDTO loginDto);
}
