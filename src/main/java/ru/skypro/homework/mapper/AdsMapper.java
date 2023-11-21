package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(uses = {AdMapper.class},componentModel = "spring")
public interface AdsMapper {

    @Mapping(source = "dto.listAdDTO", target = "result")
    AdsDTO toDTO(AdDTO dto);
}
