package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.model.User;

@Mapper
public interface UpdateUserMapper {

    UpdateUserDTO toDTO(User user);

    User toEntity(UpdateUserDTO updateUserDTO);
}
