package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.UpdateUserDTO;

@SpringMapperConfig
public interface UpdateUserMapper {

    UpdateUserDTO toDTO(UpdateUser updateUser);

    UpdateUser toEntity(UpdateUserDTO updateUserDTO);
}
