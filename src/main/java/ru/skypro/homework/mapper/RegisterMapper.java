package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "username")
    Users toEntity(RegisterDTO dto);

    @Mapping(target = "username", source = "email")
    RegisterDTO toDTO(Users users);
}


