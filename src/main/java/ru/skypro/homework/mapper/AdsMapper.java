package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.model.Ad;

@Mapper
public interface AdsMapper {

    AdsDTO toDTO(Ad ad);
    Ad toEntity(AdsDTO adsDTO);
}
