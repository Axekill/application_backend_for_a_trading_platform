package ru.skypro.homework.mapper;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.model.Ad;

@SpringMapperConfig
public interface AdMapper {

    AdDTO toDTO(Ad ad);

    Ad toEntity(AdDTO adDTO);
}
