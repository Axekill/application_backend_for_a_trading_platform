package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

import java.util.List;

public interface AdsService {


    List<AdDTO> getAllAds ();

    AdDTO addAd (CreateOrUpdateAdDTO createOrUpdateAdDto , MultipartFile image);

    ExtendedAdDTO getAdInformation (Integer id);

    void deleteAd (Integer id , Authentication authentication);








}
