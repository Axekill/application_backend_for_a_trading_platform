package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring")
public interface CreateOrUpdateAdMapper {

    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.price", target = "price")
    @Mapping(source = "dto.description", target = "description")
    Ad toEntity(CreateOrUpdateAdDTO dto);

    @Mapping(source = "ad.title", target = "title")
    @Mapping(source = "ad.price", target = "price")
    @Mapping(source = "ad.description", target = "description")
    CreateOrUpdateAdDTO toDTO(Ad ad);
}
