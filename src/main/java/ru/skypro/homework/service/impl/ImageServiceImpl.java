package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repostitory.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image uploadImage(MultipartFile multipartFile) throws IOException {
        Image image =new Image();
        image.setData(multipartFile.getBytes());
        image.setFileSize(multipartFile.getSize());
        image.setMediaType(multipartFile.getContentType());
        return imageRepository.save(image) ;
    }

    @Override
    public byte[] getImage(Long id) {
        Image image =imageRepository.findById(id).orElseThrow();
        return image.getData();
    }
}
