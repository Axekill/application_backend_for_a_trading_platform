package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface NewPasswordMapper {



    //из юзера берем пароль и передаем его в дто
    @Mapping(target = "currentPassword", source = "password")
    NewPasswordDTO toDTO(User user);


    //новый пароль из дто передаем в юзер
    @Mapping(target= "password", source = "newPassword")
    User toEntity(NewPasswordDTO dto);

  /*  @Mapping(source = "user.password", target = "currentPassword")
  //  @Mapping(source = "user.newPassword", target = "newPassword")
    NewPasswordDTO toDto(User user);

    @Mapping(source = "user.password", target = "newPassword")
    User toModel(RegisterDTO registerDto);*/
}
