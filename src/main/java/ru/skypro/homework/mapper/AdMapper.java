package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring", uses={UserMapper.class})
public interface AdMapper {

    @Mapping(source = "id", target = "author")
    AdDTO toDTO(Users users);

    AdDTO adDtoToDTO(Ad ad);

   @Mapping(source = "author", target = "id")
   Users toEntity(AdDTO dto);

    Ad adToEntity(AdDTO dto);


}

