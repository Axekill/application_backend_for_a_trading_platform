package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UsersDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDTO toDTO(Users users);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "password", ignore = true)
    Users toEntity(UsersDTO dto);


}
