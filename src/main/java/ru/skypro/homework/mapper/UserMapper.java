package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(Users users);

    Users toEntity(UserDTO dto);


}
