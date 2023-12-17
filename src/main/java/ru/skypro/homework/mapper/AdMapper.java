package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
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
    @Mapping(source = "id", target = "pk")
    AdDTO toAdDTO(Ad ad);

    @Mapping(source = "size", target = "count")
    @Mapping(source = "adList", target = "results")
    AdsDTO adsListToAdsDTO(Integer size, List<Ad> adList);

    @Mapping(source = "users.firstName", target = "authorFirstName")
    @Mapping(source = "users.lastName", target = "authorLastName")
    @Mapping(source = "users.email", target = "email")
    @Mapping(source = "users.phone", target = "phone")
    @Mapping(source = "image", target = "image", qualifiedByName = "getAdImageLink")
    @Mapping(source = "id", target = "pk")
    ExtendedAdDTO AdToExtendedDTO(Ad ad);

    Ad toEntity(CreateOrUpdateAdDTO dto);

    //    @Mapping(source = "author", target = "id")
//    Users toEntity(AdDTO dto);
    @Mapping(source = "author", target = "users.id")
    @Mapping(target = "id", source = "pk")
   @Mapping(target = "image", ignore = true)
    Ad adToEntity(AdDTO dto);

    @Named("getAdImageLink")
    default String getAdImageLink(Image image) {
        return (image == null) ? null : "/ads/image/" + image.getId();
    }
}

