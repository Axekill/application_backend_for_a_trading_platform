package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {

    @Mapping(target = "id", ignore = true)
    Users toEntity(UpdateUserDTO dto);


    UpdateUserDTO toDTO(Users users);
}
