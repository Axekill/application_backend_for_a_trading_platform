package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;

@Mapper
public interface AdMapper {

    AdDTO toDTO(Ad ad);

    Ad toEntity(AdDTO adDTO);
}
