package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "user_id", target = "author")
    @Mapping(source = "adImage", target = "image")
    AdDTO toDto(Ad ad);



    @Mapping(source = "author", target = "user_id")
    @Mapping(source = "image", target = "adImage")
    Ad toModel(AdDTO adDTO);


}

