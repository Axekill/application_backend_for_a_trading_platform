package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.User;

@Mapper
public interface RegisterMapper {

    RegisterDTO toDto(User user);

    User toEntity(RegisterDTO registerDTO);
}
