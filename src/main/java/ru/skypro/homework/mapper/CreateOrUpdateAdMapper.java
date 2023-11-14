package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

@Mapper
public interface CreateOrUpdateAdMapper {
    CreateOrUpdateAdMapper INSTANCE = Mappers.getMapper(CreateOrUpdateAdMapper.class);
    @Mapping(source = "title",target = "ad.title" )
    @Mapping( source = "price",target = "ad.price")
    @Mapping(source = "description",target = "ad.description" )
    Ad toModel(CreateOrUpdateAdDTO createOrUpdateAdDto);
}
