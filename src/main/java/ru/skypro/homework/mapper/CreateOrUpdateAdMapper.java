package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring")
public interface CreateOrUpdateAdMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    Ad toEntity(CreateOrUpdateAdDTO dto);


    CreateOrUpdateAdDTO toDTO(Ad ad);
}
