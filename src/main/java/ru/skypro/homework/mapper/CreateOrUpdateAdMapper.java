package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

@Mapper
public interface CreateOrUpdateAdMapper {

    CreateOrUpdateAdDTO toDTO(Ad ad);

    Ad toEntity(CreateOrUpdateAdDTO createOrUpdateAdDTO);
}
