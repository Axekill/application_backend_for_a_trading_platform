package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.model.Ads;

@SpringMapperConfig
public interface AdsMapper {

    AdsDTO toDTO(Ads ads);
    Ads toEntity(AdsDTO adsDTO);
}
