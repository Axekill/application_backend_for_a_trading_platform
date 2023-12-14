package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsersMapper.class})
public interface AdMapper {

    //    @Mapping(source = "id", target = "author")
//    AdDTO toDTO(Users users);
    @Mapping(source = "image", target = "image", qualifiedByName = "getAdImageLink")
    @Mapping(target = "author", source = "users.id")
    AdDTO toAdDTO(Ad ad);

    @Mapping(source = "size", target = "count")
    @Mapping(source = "adList", target = "result")
    AdsDTO adsListToAdsDTO(Integer size, List<Ad> adList);

//    @Mapping(source = "author", target = "id")
//    Users toEntity(AdDTO dto);

    //  Ad adToEntity(AdDTO dto);

    @Named("getAdImageLink")
    default String getAdImageLink(Image image) {
        return (image == null) ? null : "/ad/image/" + image.getId();
    }
}

