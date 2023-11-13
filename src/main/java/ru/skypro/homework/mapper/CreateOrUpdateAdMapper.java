package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;

@SpringMapperConfig
public interface CreateOrUpdateAdMapper {

    CreateOrUpdateAdDTO toDTO(CreateOrUpdateAd createOrUpdateAd);

    CreateOrUpdateAd toEntity(CreateOrUpdateAdDTO createOrUpdateAdDTO);
}
