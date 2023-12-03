package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

import java.io.IOException;

public interface ImageService {
    /***
     * загружаем картинку
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    Image uploadImage (MultipartFile multipartFile) throws IOException;

    /***
     * Получаем картинку
     * @param id
     * @return
     */
    byte[] getImage(Long id);
}
