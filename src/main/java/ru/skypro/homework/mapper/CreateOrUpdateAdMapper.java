package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.CreateOrUpdateAd;

@SpringMapperConfig
public interface CreateOrUpdateAdMapper {

    CreateOrUpdateAdDTO toDTO(CreateOrUpdateAd createOrUpdateAd);

    CreateOrUpdateAd toEntity(CreateOrUpdateAdDTO createOrUpdateAdDTO);
}
