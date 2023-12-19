package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUsersDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface UpdateUsersMapper {

    @Mapping(target = "id", ignore = true)
    Users toEntity(UpdateUsersDTO dto);


    UpdateUsersDTO toDTO(Users users);
}
