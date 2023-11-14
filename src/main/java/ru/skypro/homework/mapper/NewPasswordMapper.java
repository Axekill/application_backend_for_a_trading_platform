package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.User;

@Mapper
public interface NewPasswordMapper {

    NewPasswordMapper INSTANCE = Mappers.getMapper(NewPasswordMapper.class);

    @Mapping(source = "user.password", target = "currentPassword")
  //  @Mapping(source = "user.newPassword", target = "newPassword")
    NewPasswordDTO toDto(User user);

    @Mapping(source = "user.password", target = "newPassword")
    User toModel(RegisterDTO registerDto);
}
