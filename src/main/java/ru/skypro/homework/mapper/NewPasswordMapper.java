package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface NewPasswordMapper {



    //из юзера берем пароль и передаем его в дто
    @Mapping(target = "currentPassword", source = "password")
    NewPasswordDTO toDTO(Users users);


    //новый пароль из дто передаем в юзер
    @Mapping(target= "password", source = "newPassword")
    Users toEntity(NewPasswordDTO dto);

  /*  @Mapping(source = "user.password", target = "currentPassword")
  //  @Mapping(source = "user.newPassword", target = "newPassword")
    NewPasswordDTO toDto(User user);

    @Mapping(source = "user.password", target = "newPassword")
    User toModel(RegisterDTO registerDto);*/
}
