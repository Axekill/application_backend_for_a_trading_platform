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

    //из юзера берем пароль и передаем его в дто
    @Mapping(source = "password", target = "dto.currentPassword")
    User toEntity(NewPasswordDTO dto);

    //новый пароль из дто передаем в юзер
    @Mapping(source = "newPassword", target = "user.password")
    NewPasswordDTO toDTO(User user);

  /*  @Mapping(source = "user.password", target = "currentPassword")
  //  @Mapping(source = "user.newPassword", target = "newPassword")
    NewPasswordDTO toDto(User user);

    @Mapping(source = "user.password", target = "newPassword")
    User toModel(RegisterDTO registerDto);*/
}
