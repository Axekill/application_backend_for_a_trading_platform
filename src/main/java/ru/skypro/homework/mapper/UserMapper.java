package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(Users users);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "password", ignore = true)
    Users toEntity(UserDTO dto);


}
