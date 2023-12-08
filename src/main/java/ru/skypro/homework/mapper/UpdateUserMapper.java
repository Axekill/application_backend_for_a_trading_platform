package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {

  /*  @Mapping(source = "dto.firstName", target = "firstName")
    @Mapping(source = "dto.lastName", target = "lastName")
    @Mapping(source = "dto.phone", target = "phone")*/
    Users toEntity(UpdateUserDTO dto);

 /*   @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.phone", target = "phone")*/
    UpdateUserDTO toDTO(Users users);
}
