package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.model.User;

@Mapper
public interface LoginMapper {

    LoginDTO toDTO(User user);

    User toEntity(LoginDTO loginDTO);
}
