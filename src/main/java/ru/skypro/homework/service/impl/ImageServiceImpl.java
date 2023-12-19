package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repostitory.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image uploadImage(MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setData(multipartFile.getBytes());
        image.setFileSize(multipartFile.getSize());
        image.setMediaType(multipartFile.getContentType());
        log.info("Изображение было сохранено");
        return imageRepository.save(image);
    }

    @Override
    public byte[] getImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow();
        return image.getData();
    }

    @Override
    public Image createImage(MultipartFile imageFile, Ad ad) {
        Image imageAd = new Image();
        extractInfoFromFile(imageFile, imageAd);
        imageAd.setAd(ad);
        return imageRepository.save(imageAd);
    }

    @Override
    public void extractInfoFromFile(MultipartFile file, Image imageToSave) {
        if (file.isEmpty()) {
            log.warn("File '{}' is empty!", file.getOriginalFilename());
            throw new RuntimeException();
        }
        byte[] imageData;
        try {
            imageData = file.getBytes();
        } catch (IOException e) {
            log.error("File '{}' has some problems and cannot be read.", file.getOriginalFilename());
            throw new RuntimeException("Problems with uploaded image");
        }
        imageToSave.setData(imageData);
        imageToSave.setFileSize(file.getSize());
        imageToSave.setMediaType(file.getContentType());
    }
}
