package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    User toEntity(LoginDTO dto);


    LoginDTO toDTO(User user);
}
