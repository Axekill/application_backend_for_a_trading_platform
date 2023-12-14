package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.UsersDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    @Mapping(source = "image",target = "image",qualifiedByName ="getUsersAvatarLink" )
    UsersDTO toDTO(Users users);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "password", ignore = true)
    Users toEntity(UsersDTO dto);

    @Named("getUsersAvatarLink")
    default String getUsersAvatarLink(Image image) {
        return (image == null) ? null : "/users/image/" + image.getId();
    }
}
