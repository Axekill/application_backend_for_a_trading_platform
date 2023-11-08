package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

@SpringMapperConfig
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
