package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.LoginDTO;

@SpringMapperConfig
public interface LoginMapper {

    LoginDTO toDTO(Login login);

    Login toEntity(LoginDTO loginDTO);
}
