package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring", uses={UserMapper.class})
public interface AdMapper {

    @Mapping(source = "id", target = "author")
    AdDTO toDTO(User user);

    AdDTO adDtoToDTO(Ad ad);

   @Mapping(source = "author", target = "id")
    User toEntity(AdDTO dto);

    Ad adToEntity(AdDTO dto);


}

