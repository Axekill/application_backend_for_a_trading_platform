package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    Users toEntity(LoginDTO dto);


    LoginDTO toDTO(Users users);
}
