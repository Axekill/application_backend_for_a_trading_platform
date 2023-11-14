package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;

@Mapper
public interface ExtendedAdMapper {

    ExtendedAdDTO toDTO(Ad ad);

    Ad toEntity(ExtendedAdDTO extendedAdDTO);
}
