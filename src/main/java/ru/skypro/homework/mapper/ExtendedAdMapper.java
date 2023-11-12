package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.ExtendedAdDTO;

@SpringMapperConfig
public interface ExtendedAdMapper {

    ExtendedAdDTO toDTO(ExtendedAd extendedAd);

    ExtendedAd toEntity(ExtendedAdDTO extendedAdDTO);
}
