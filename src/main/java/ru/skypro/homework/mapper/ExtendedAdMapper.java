package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.ExtendedAd;

@SpringMapperConfig
public interface ExtendedAdMapper {

    ExtendedAdDTO toDTO(ExtendedAd extendedAd);

    ExtendedAd toEntity(ExtendedAdDTO extendedAdDTO);
}
